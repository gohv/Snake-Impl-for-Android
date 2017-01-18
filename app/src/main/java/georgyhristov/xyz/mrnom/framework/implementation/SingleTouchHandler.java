package georgyhristov.xyz.mrnom.framework.implementation;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import georgyhristov.xyz.mrnom.framework.Input;
import georgyhristov.xyz.mrnom.framework.Pool;

/**
 * Created by gohv on 17.01.17.
 */

public class SingleTouchHandler implements TouchHandler{

    private boolean isTouched;
    private int touchX;
    private int touchY;
    private float scaleX;
    private float scaleY;

    Pool<Input.TouchEvent> touchEventPool;
    List<Input.TouchEvent> touchEvents = new ArrayList<>();
    List<Input.TouchEvent> touchEventsBuffer = new ArrayList<>();


    public SingleTouchHandler(View view,float scaleX, float scaleY) {

        Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        touchEventPool = new Pool<>(factory,100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            if(pointer == 0)return isTouched;
            else return false;
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {return touchX;}
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {return touchY;}
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int len = touchEvents.size();
            for(Input.TouchEvent event : touchEvents) {
                touchEventPool.free(event);
            }
                touchEvents.clear();
                touchEvents.addAll(touchEventsBuffer);
                touchEventsBuffer.clear();
                return touchEvents;
            }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        synchronized (this){
            Input.TouchEvent touchEvent = touchEventPool.newObject();

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = touchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = touchEvent.TOUCH_DRAGGED;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = touchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }
            touchEvent.x = touchX= (int)(event.getX() * scaleX);
            touchEvent.y = touchY = (int) (event.getY()* scaleY);
            touchEventsBuffer.add(touchEvent);
            return true;
        }
     }
}

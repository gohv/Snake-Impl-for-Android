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

public class MultiTouchHandler implements TouchHandler {

    private final int MAX_TOUCH_POINTS = 10;
    private boolean[] isTouched = new boolean[MAX_TOUCH_POINTS];
    private int[] touchX = new int[MAX_TOUCH_POINTS];
    private int[] touchY = new int[MAX_TOUCH_POINTS];
    private int[] id = new int[MAX_TOUCH_POINTS];
    Pool<Input.TouchEvent> touchEventPool;
    List<Input.TouchEvent> touchEvents = new ArrayList<>();
    List<Input.TouchEvent> touchEventsBuffer = new ArrayList<>();
    private float scaleX;
    private float scaleY;


    public MultiTouchHandler(View view,float scaleX, float scaleY) {
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
            int index = getIndex(pointer);
            if(index < 0 || index >= MAX_TOUCH_POINTS){
                return false;
            }else {
                return isTouched[index];
            }
        }

    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index < 0 || index >= MAX_TOUCH_POINTS){
                return 0;
            }else {
                return touchX[index];
            }
        }
    }

    private int getIndex(int pointer) {
        for(int i = 0; i < MAX_TOUCH_POINTS; i++){
            if(id[i] == pointer) return i;
        }
        return -1;
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index < 0 || index >= MAX_TOUCH_POINTS){
                return 0;
            }else {
                return touchY[index];
            }
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int len = touchEvents.size();
            for (Input.TouchEvent t : touchEvents) {
                touchEventPool.free(t);
            }
                touchEvents.clear();
                touchEvents.addAll(touchEventsBuffer);
                touchEventsBuffer.clear();
                return touchEvents;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointerCount = event.getPointerCount();
        Input.TouchEvent touchEvent;
        for (int i = 0; i < MAX_TOUCH_POINTS; i++){
            if(i >= pointerCount){
                isTouched[i] = false;
                id[i] = -1;
                continue;
            }
            int pointerId = event.getPointerId(i);
            if(event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex){
                continue;
            }
            switch (action){
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
                    touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
                    isTouched[i] = true;
                    id[i] = pointerId;
                    touchEventsBuffer.add(touchEvent);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
                    touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
                    isTouched[i] = false;
                    id[i] = -1;
                    touchEventsBuffer.add(touchEvent);
                    break;

                case MotionEvent.ACTION_MOVE:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
                    touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
                    isTouched[i] = true;
                    id[i] = pointerId;
                    touchEventsBuffer.add(touchEvent);
                    break;
        }
            }
        return true;
    }
}

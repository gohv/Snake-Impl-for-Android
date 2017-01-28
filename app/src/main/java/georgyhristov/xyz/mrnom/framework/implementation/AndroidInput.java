package georgyhristov.xyz.mrnom.framework.implementation;

import android.content.Context;
import android.view.View;

import junit.runner.Version;

import java.util.List;

import georgyhristov.xyz.mrnom.framework.Input;

/**
 * Created by gohv on 17.01.17.
 */

public class AndroidInput implements Input {

    private AccelerometerHandler accelerometerHandler;
    private KeyboardHandler keyboardHandler;
    private TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX,float scaleY) {
        accelerometerHandler = new AccelerometerHandler(context);
        keyboardHandler = new KeyboardHandler(view);

        touchHandler = new MultiTouchHandler(view,scaleX,scaleY);
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return keyboardHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() {
        return accelerometerHandler.getAccel1x();
    }

    @Override
    public float getAccelY() {
        return accelerometerHandler.getAccel1y();
    }

    @Override
    public float getAccelZ() {
        return accelerometerHandler.getAccel1z();
    }

    @Override
    public List<KeyEvent> getKeyEvent() {
        return keyboardHandler.getKeyEvents();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}

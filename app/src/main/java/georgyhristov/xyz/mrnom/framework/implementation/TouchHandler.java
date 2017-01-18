package georgyhristov.xyz.mrnom.framework.implementation;

import android.view.View;

import java.util.List;

import georgyhristov.xyz.mrnom.framework.Input;

/**
 * Created by gohv on 17.01.17.
 */

public interface TouchHandler extends View.OnTouchListener {

    boolean isTouchDown(int pointer);
    int getTouchX(int pointer);
    int getTouchY(int pointer);
    List<Input.TouchEvent> getTouchEvents();


}

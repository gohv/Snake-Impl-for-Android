package georgyhristov.xyz.mrnom.MrNom;

import java.util.List;
import java.util.Vector;

import georgyhristov.xyz.mrnom.framework.Input;
import georgyhristov.xyz.mrnom.framework.math.Vector2;

/**
 * Created by gohv on 31.01.17.
 */

public class Fling {
    public static final int LEFT = 0;
    public static final int RIGHT = 0;
    public static final int UP = 0;
    public static final int DOWN = 0;
    final Vector2 startPosition = new Vector2();
    final Vector2 endPosition = new Vector2();
    final Vector2 direction = new Vector2();
    boolean inFling = false;

    public boolean processEvents(List<Input.TouchEvent> touchEvents) {

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_DOWN) {
                startPosition.set(event.x, event.y);
                return false;
            }
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                endPosition.set(event.x, event.y);
                direction.set(endPosition).sub(startPosition);
                return true;
            }
        }
        return true;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public int getDominantDirection() {
        if(Math.abs(direction.x) > Math.abs(direction.y)) {
            return direction.x < 0?LEFT:RIGHT;
        } else {
            return direction.y < 0?UP:DOWN;
        }
    }
}


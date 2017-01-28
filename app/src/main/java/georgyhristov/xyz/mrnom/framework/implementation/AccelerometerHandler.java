package georgyhristov.xyz.mrnom.framework.implementation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by gohv on 12.01.17.
 */

public class AccelerometerHandler implements SensorEventListener {
    private float accel1x, accel1y, accel1z;

    public AccelerometerHandler(Context context) {
        SensorManager manager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float getAccel1x() {
        return accel1x;
    }

    public float getAccel1y() {
        return accel1y;
    }

    public float getAccel1z() {
        return accel1z;
    }
}

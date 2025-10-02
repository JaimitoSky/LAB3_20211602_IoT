package com.example.lab3_20211602_iot.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {
    public interface Listener { void onShake(); }

    private final SensorManager sm;
    private final Sensor acc;
    private final Listener listener;
    private final float threshold;
    private long lastTs;

    public ShakeDetector(Context ctx, float threshold, Listener listener) {
        this.sm = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        this.acc = sm != null ? sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) : null;
        this.listener = listener;
        this.threshold = threshold;
    }

    public void start() {
        if (sm != null && acc != null) sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop() {
        if (sm != null) sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0], y = event.values[1], z = event.values[2];
        double mag = Math.sqrt(x*x + y*y + z*z);
        long now = System.currentTimeMillis();
        if (mag > threshold && now - lastTs > 800) {
            lastTs = now;
            if (listener != null) listener.onShake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}

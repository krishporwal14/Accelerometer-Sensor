package com.example.sensoraccelerometerex;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    SensorManager sm;
    List l1;

    SensorEventListener sel = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent se) {
            float[] values = se.values;
            tv1.setText("x: " + values[0] + "\ny: " + values[1] + "\nz: " + values[2]);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        tv1 = findViewById(R.id.tv1);
        l1 = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(l1.size() > 0) {
            sm.registerListener(sel, (Sensor) l1.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "No Sensor", Toast.LENGTH_SHORT).show();
        }
    }
}
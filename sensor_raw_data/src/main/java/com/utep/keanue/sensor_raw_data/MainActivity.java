package com.utep.keanue.sensor_raw_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//My Imports
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;


/** Class that will demonstrate the use of Accelometer (Sensor).
 * implement: SensorEventListener */
public class MainActivity extends AppCompatActivity implements SensorEventListener{

    /** Create private objects to use in application */
    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;

    /** onCreate Method.
     * First thing the application will do once started */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Create Accelometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register Sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextViews
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);



    }//end onCreate


    /** Sensor Changed
     * This will change the TextView to the values of the sensor */
    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X = "+event.values[0]);
        yText.setText("Y = "+event.values[1]);
        zText.setText("Z = "+event.values[2]);
    }

    /** Method from interface (NOT USED) */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Not Used
    }
}//end class

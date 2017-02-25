package com.utep.keanue.sensor_accelometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//My Imports
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//File Helper Class


/** Class that will demonstrate the use of Accelometer (Sensor).
 * implement: SensorEventListener */
public class MainActivity extends AppCompatActivity implements SensorEventListener{

    /** Create private objects to use in application */
    private TextView xText, yText, zText, longText, latText;
    private Button btn_save, btn_read, btn_delete;
    private Sensor mySensor;
    private SensorManager SM;

    private TextView txtContent;

    /** onCreate Method.
     * First thing the application will do once started */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Accelerometer Variables
        //Create Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        //Create Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Register Sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign Accelerometer
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

        //Assign GPS Location
        longText = (TextView)findViewById(R.id.long_text);
        latText = (TextView)findViewById(R.id.lat_text);

        //Assign Buttons
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_read = (Button)findViewById(R.id.btn_read);
        btn_delete = (Button)findViewById(R.id.btn_delete);

        //Time Stamp
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date());

        Log.d("MainActivity", "Current Timestamp: " + format);

        //Local Concatenated Text
        final String full_data = (format + " "+xText.getText()+" "+yText.getText()+" "+zText.getText()+".");

        Log.d("MainActivity", "INFO: " + full_data);

        //Display Saved Data
        txtContent = (TextView) findViewById(R.id.txtContent);

        //TODO ADD CODE TO INSERT FILE HELPER. JAVA

        // Save on File
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FileHelper.saveToFile( full_data)){
                    Toast.makeText(MainActivity.this,"Saved to file",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Error save file!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Read File
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtContent.setText(FileHelper.ReadFile(MainActivity.this));
            }
        });


        // Delete File
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File fileName = new File(FileHelper.path+FileHelper.fileName);
                if (FileHelper.deleteFile(fileName)){
                    Toast.makeText(MainActivity.this,"Deleted file",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Error delete file!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//end onCreate

    /***/

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

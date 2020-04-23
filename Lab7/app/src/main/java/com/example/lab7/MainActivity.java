package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import gps.location.GPStrace;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button btnShowLocation;
    GPStrace gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button)findViewById(R.id.button);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPStrace(MainActivity.this);
                if (gps.getLocation() != null) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Toast.makeText(getApplicationContext(), "Your Location is \nLat:"
                            + latitude + "\nLong:" + longitude, Toast.LENGTH_LONG).show();
                } else {
                    //gps.showSettingAlert();
                }
            }
        });
    }
}

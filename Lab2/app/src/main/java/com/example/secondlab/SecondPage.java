package com.example.secondlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        Intent i = getIntent();
        String nameValue = i.getStringExtra("username");
        String usnValue = i.getStringExtra("usn");
        String depValue = i.getStringExtra("dep");

        TextView nameField = (TextView) findViewById(R.id.textView);
        TextView usnField = (TextView) findViewById(R.id.textView2);
        TextView depField = (TextView) findViewById(R.id.textView4);

        nameField.setText(nameValue);
        usnField.setText(usnValue);
        depField.setText(depValue);

    }


}

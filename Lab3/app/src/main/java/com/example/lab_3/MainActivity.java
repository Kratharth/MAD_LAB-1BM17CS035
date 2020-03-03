package com.example.lab_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eventHandler(View view) {
        String result=null,operation = null;
        EditText e1 = findViewById(R.id.editText);
        EditText e2 = findViewById(R.id.editText2);
        TextView t1 = findViewById(R.id.textView);
        int num1 = Integer.parseInt(e1.getText().toString());
        int num2 = Integer.parseInt(e2.getText().toString());
        if(view.getId()==R.id.button)
        {
            result = Integer.toString((num1+num2));
            operation = "+";
        }
        else if(view.getId()==R.id.button2)
        {
            result = Integer.toString((num1-num2));
            operation = "-";
        }
        else if(view.getId()==R.id.button3)
        {
            result = Integer.toString((num1*num2));
            operation = "*";
        }
        else if(view.getId() == R.id.button4)
        {
            result = Integer.toString((num1/num2));
            operation = "/";
        }
        t1.setText(Integer.toString(num1)+operation+Integer.toString(num2)+"="+result);
    }
}

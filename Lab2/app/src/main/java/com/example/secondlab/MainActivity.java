package com.example.secondlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.editText3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.department, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void submitDetails(View view){
        EditText nameField = (EditText) findViewById(R.id.editText);
        EditText usnField = (EditText) findViewById(R.id.editText2);
        Spinner spinner = (Spinner) findViewById(R.id.editText3);

        Intent i = new Intent(MainActivity.this, SecondPage.class);

        i.putExtra("username", nameField.getText().toString());
        i.putExtra("usn", usnField.getText().toString());
        i.putExtra("dep", spinner.getSelectedItem().toString());
        startActivity(i);

    }


}

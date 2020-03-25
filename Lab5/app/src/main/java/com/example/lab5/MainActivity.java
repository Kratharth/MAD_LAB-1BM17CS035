package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    SQLiteDatabase db;
    Button insert,view;
    EditText name,USN,marks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        USN = findViewById(R.id.studentUSN);
        marks = findViewById(R.id.marks);
        insert = findViewById(R.id.insert);
        view = findViewById(R.id.view);
        insert.setOnClickListener(this);
        view.setOnClickListener(this);
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(USN VARCHAR,name VARCHAR,marks VARCHAR);");
    }

    @Override
    public void onClick(View v) {
        if(v==insert)
        {
            if(USN.getText().toString().trim().length()==0||
                    name.getText().toString().trim().length()==0||
                    marks.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO student VALUES('"+USN.getText()+"','"+name.getText()+
                    "','"+marks.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(v==view)
        {
            // Checking for empty roll number
            if(USN.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE USN='"+USN.getText()+"'", null);
            if(c.moveToFirst())
            {
                name.setText(c.getString(1));
                marks.setText(c.getString(2));
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }
        }
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        USN.setText("");
        name.setText("");
        marks.setText("");
        USN.requestFocus();
    }
}

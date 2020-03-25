package com.example.lab6;

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
    Button insert,view,viewall,update,delete,clear;
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
        viewall = findViewById(R.id.viewall);
        delete = findViewById(R.id.delete);
        clear = findViewById(R.id.clear);
        update = findViewById(R.id.update);

        insert.setOnClickListener(this);
        view.setOnClickListener(this);
        delete.setOnClickListener(this);
        clear.setOnClickListener(this);
        update.setOnClickListener(this);
        viewall.setOnClickListener(this);

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
        if(v==clear)
        {
            clearText();
        }
        if(v==delete){
            if(USN.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+USN.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student WHERE rollno='"+USN.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if(v==update)
        {
            if(USN.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+USN.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE student SET name='"+ name.getText() + "',marks='"+ marks.getText() +
                        "' WHERE rollno='"+USN.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else{
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if(v==viewall)
        {
            Cursor c=db.rawQuery("SELECT * FROM student", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("USN: "+c.getString(0)+"\n");
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("Marks: "+c.getString(2)+"\n\n");
            }
            showMessage("Student Details", buffer.toString());
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

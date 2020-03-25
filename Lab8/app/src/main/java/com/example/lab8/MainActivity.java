package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button read = findViewById(R.id.read);
        Button write = findViewById(R.id.write);
        Button clear = findViewById(R.id.clear);
        final EditText e1 = findViewById(R.id.editText);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = e1.getText().toString();
                try {
                    File f1 = new File("/sdcard/file1.txt");
                    f1.createNewFile();
                    FileOutputStream fout = new FileOutputStream(f1);
                    fout.write(message.getBytes());
                    fout.close();
                    Toast.makeText(getBaseContext(),"Data written into sd card",Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                String buff = "";
                File f1 = new File("/sdcard/file1.txt");
                try {
                    FileInputStream fin = new FileInputStream(f1);
                    BufferedReader bf = new BufferedReader(new InputStreamReader(fin));
                    while((message=bf.readLine())!=null)
                    {
                        buff+=message;
                    }
                    e1.setText(buff);
                    bf.close();
                    fin.close();
                    Toast.makeText(getBaseContext(),"Data Received from SD Card",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}

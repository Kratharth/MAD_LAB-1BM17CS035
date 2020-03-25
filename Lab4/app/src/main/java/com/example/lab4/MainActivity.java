package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bg = Bitmap.createBitmap(720,1280,Bitmap.Config.ARGB_8888);
        ImageView i = findViewById(R.id.imageview1);
        i.setBackgroundDrawable(new BitmapDrawable(bg));
        Canvas ca = new Canvas(bg);
        Paint pa = new Paint();
        pa.setColor(Color.RED);
        pa.setTextSize(50);
        ca.drawText("Rectangle", 420, 150, pa);
        ca.drawRect(400, 200, 650, 700, pa);

        //To draw a Circle
        ca.drawText("Circle", 120, 150, pa);
        ca.drawCircle(200, 350, 150, pa);

        //To draw a Square
        ca.drawText("Square", 120, 800, pa);
        ca.drawRect(50, 850, 350, 1150, pa);

        //To draw a Line
        ca.drawText("Line", 480, 800, pa);
        ca.drawLine(520, 850, 520, 1150, pa);
    }
}

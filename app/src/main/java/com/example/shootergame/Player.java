package com.example.shootergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

class Player  {

    private double posx;
    private double posy;
    private int radius;
    private Paint paint;


    Player(Context context, double posx, double posy, int radius) {
        super();
        this.radius = radius;
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.red);
        paint.setColor(color);

        
    }

    public void update() {

    }


    public void draw(Canvas canvas) {
        canvas.drawCircle((float) posx, (float)posy, (float) radius, paint);
    }

    public void setPosition(double posxx, double posy) {
        this.posx = posx;
        this.posy = posy;
    }
}

package com.example.shootergame.Object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Circle extends GameObject {

    protected int radius;
    protected Paint paint;

    Circle(Context context, int color, double posx, double posy, int radius) {
        super(posx, posy);
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float) posx, (float)posy, (float) radius, paint);
    }
}
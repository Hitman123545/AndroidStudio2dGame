package com.example.shootergame.Object;

import android.content.Context;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.shootergame.GameLoop;
import com.example.shootergame.Joystick;
import com.example.shootergame.R;

public class Player extends Circle {

    private Joystick joystick;
    private static final double pps = 400.0;
    private static final double speed = pps / GameLoop.UPSmax;


    Player(Context context, Joystick joystick, double posx, double posy, int radius) {
        super(context, ContextCompat.getColor(context, R.color.player), posx, posy, radius);
        this.radius = radius;
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
        this.joystick = joystick;

    }

    @Override
    public void update() {
        posx += joystick.getActuatorX()* speed;
        posy += joystick.getActuatorY()* speed;
    }

    public void setPosition(double posx, double posy) {
        this.posx = posx;
        this.posy = posy;
    }
}

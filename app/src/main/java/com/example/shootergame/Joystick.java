package com.example.shootergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

class Joystick {

    private int outercirclexposX;
    private int outercirclexposY;
    private int innercirclexposX;
    private int innercirclexposY;
    private int outercircleradius;
    private int innercircleradius;
    private Paint outercirclepaint;
    private Paint innercirclepaint;
    private double joystickcentertouchdistance;
    private boolean ispressed;
    private double actuatorX;
    private double actuatorY;



    Joystick( int posX, int posY, int outtercirleradius, int innercircleradius) {

        outercirclexposX = posX;
        outercirclexposY = posY;
        innercirclexposX = posX;
        innercirclexposY = posY;
        this.outercircleradius = outtercirleradius;
        this.innercircleradius = innercircleradius;

        outercirclepaint = new Paint();
        outercirclepaint.setColor(Color.GRAY);

        innercirclepaint = new Paint();
        innercirclepaint.setColor(Color.BLUE);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle( (float) outercirclexposX, (float) outercirclexposY, (float) outercircleradius, outercirclepaint);
        canvas.drawCircle( (float) innercirclexposX, (float) innercirclexposY, (float) innercircleradius, innercirclepaint);
    }

    public void update() {
        innercirclexposX = (int) (outercirclexposX + actuatorX * outercircleradius);
        innercirclexposY = (int) (outercirclexposY + actuatorY * outercircleradius);
    }

    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX = touchPositionX - outercirclexposX;
        double deltaY = touchPositionY - outercirclexposY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        if(deltaDistance < outercircleradius) {
            actuatorX = deltaX/outercircleradius;
            actuatorY = deltaY/outercircleradius;
        } else {
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }


    public boolean isPressed(double touchposx, double touchposy) {
        joystickcentertouchdistance = Math.sqrt(
                Math.pow(outercirclexposX - touchposx, 2) +
                Math.pow(outercirclexposY - touchposy, 2)
        );
        return joystickcentertouchdistance < outercircleradius;
    }

    public void reset() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public void setIsPressed(boolean value) {
        ispressed = value;
    }

    public boolean getPressed() {
        return ispressed;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}

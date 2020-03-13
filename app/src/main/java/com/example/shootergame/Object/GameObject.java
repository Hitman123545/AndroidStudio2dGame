package com.example.shootergame.Object;

import android.graphics.Canvas;

public abstract class GameObject {

    protected double posx;
    protected double posy;
    protected double velx;
    protected double vely;

    GameObject(double posx, double posy) {
        this.posx = posx;
        this.posy = posy;

    }

    protected static double distanceBetweenObjects(GameObject object1, GameObject object2) {

        return Math.sqrt(
                Math.pow( object2.getposx() + object1.getposx(), 2) +
                Math.pow( object2.getposy() + object1.getposy(), 2)
        );
    }

    public abstract void draw(Canvas canvas);

    public abstract void update();

    protected double getposx() { return posx; }

    protected double getposy() { return posy; }
}

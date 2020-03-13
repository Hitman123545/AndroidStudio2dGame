package com.example.shootergame.Object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.shootergame.GameLoop;
import com.example.shootergame.R;

public class Enemy extends Circle{

    Player player;
    private static final double pps = 400.0;
    private static final double speed = pps / GameLoop.UPSmax;

    Enemy(Context context, double posx, double posy, int radius, Player player){
        super(context, ContextCompat.getColor(context, R.color.enemy),posx,posy,radius);
        this.player = player;
    }

    @Override
    public void update() {

        double distanceToPlayerX = player.getposx() - this.posx;
        double distanceToPlayerY = player.getposy() - this.posy;

        double distanceToPlayer = GameObject.distanceBetweenObjects(this, player);

        double directionx = distanceToPlayerX / distanceToPlayer;
        double directiony = distanceToPlayerY / distanceToPlayer;

        if (distanceToPlayer > 0) {
            velx = directionx / speed * 0.6;
            vely = directiony / speed * 0.6;

        } else {
            velx = 0;
            vely = 0;
        }

        posx += velx;
        posy += vely;

    }
}

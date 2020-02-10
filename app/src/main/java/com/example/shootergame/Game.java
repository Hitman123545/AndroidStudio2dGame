package com.example.shootergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;

class Game extends SurfaceView implements SurfaceHolder.Callback {

    GameLoop gameLoop = new GameLoop();
    Context context;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop();
        this.context = context;

        setFocusable(true);
    }

    public static void draw() {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFPS(canvas);
        drawUPS(canvas);
    }

    public void drawUPS (Canvas canvas){
        String avgUPS = Double.toString(gameLoop.getAvgUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.green);
        paint.setColor(color);
        paint.setTextSize(40);
        canvas.drawText("UPS: "+avgUPS, 100, 20, paint );
    }

    public void drawFPS (Canvas canvas){
        String avgFPS = Double.toString(gameLoop.getAvgFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.green);
        paint.setColor(color);
        paint.setTextSize(40);
        canvas.drawText("UPS: "+avgFPS, 100, 200, paint );
    }

    public void update() {
    }
}

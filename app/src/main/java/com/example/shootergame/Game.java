package com.example.shootergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;

class Game extends SurfaceView implements SurfaceHolder.Callback {

    GameLoop gameLoop;
    Player player;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        player = new Player();

        setFocusable(true);
    }

    public static void draw() {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        gameLoop.startLoop();
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

        player.draw(canvas);

    }

    public void drawUPS (Canvas canvas){
        String avgUPS = Double.toString(gameLoop.getAvgUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.green);
        paint.setColor(color);
        paint.setTextSize(40);
        canvas.drawText("UPS: "+avgUPS, 100, 40, paint );
    }

    public void drawFPS (Canvas canvas){
        String avgFPS = Double.toString(gameLoop.getAvgFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.green);
        paint.setColor(color);
        paint.setTextSize(40);
        canvas.drawText("FPS: "+avgFPS, 100, 200, paint );
    }

    public void update() {

        player.update();
    }
}

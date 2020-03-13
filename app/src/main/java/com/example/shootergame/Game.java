package com.example.shootergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.shootergame.Object.Enemy;
import com.example.shootergame.Object.Player;

class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Joystick joystick;
    private final Enemy enemy;
    private GameLoop gameLoop;
    private final Player player;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        joystick = new Joystick(100,600,80,40);
        player = new Player(getContext(), joystick, 500, 500, 40);
        enemy = new Enemy(getContext(),1000,1000,40, player);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                if (joystick.isPressed((double) event.getX(),(double) event.getY())) {
                    joystick.setIsPressed(true);
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                if (joystick.getPressed()) {
                    joystick.setActuator((double) event.getX(),(double) event.getY());
                }

                return true;

            case MotionEvent.ACTION_UP:
                joystick.reset();
                joystick.setIsPressed(false);

        }

        return super.onTouchEvent(event);
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

        joystick.draw(canvas);
        player.draw(canvas);
        enemy.draw(canvas);

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
        canvas.drawText("FPS: "+avgFPS, 100, 100, paint );
    }

    public void update() {
        player.update();
        enemy.update();
        joystick.update();
    }
}

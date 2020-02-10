package com.example.shootergame;

import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

class GameLoop extends Thread {

    private Game game;
    private boolean isrunning;
    private SurfaceHolder surfaceHolder;
    private double avgUPS;
    private double avgFPS;
    private final double UPSmax = 30.0;
    private final double UPSperiod = 1E+3 / UPSmax;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAvgUPS() {
        return this.avgUPS;
    }

    public double getAvgFPS() {
        return this.avgFPS;
    }

    public void startLoop() {
        isrunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();
        Canvas canvas = null;

        int updatecount = 0;
        int framecount = 0;

        long starttime;
        long passedtime;
        long sleeptime;

        starttime = System.currentTimeMillis();

        while (isrunning) {

            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updatecount++;

                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        framecount++;
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }



            passedtime = System.currentTimeMillis() - starttime;
            sleeptime =  (long) (updatecount * UPSperiod - passedtime);
            if (sleeptime > 0) {

                try {
                    sleep(sleeptime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (sleeptime < 0 && updatecount < UPSmax) {

                game.update();
                updatecount++;
                passedtime = System.currentTimeMillis() - starttime;
                sleeptime =  (long) (updatecount * UPSperiod - passedtime);
            }

            passedtime = System.currentTimeMillis() - starttime;
            if (passedtime >= 1000) {
                avgUPS = updatecount / (1E-3*passedtime);
                avgFPS = framecount / (1E-3*passedtime);
                framecount = 0;
                updatecount = 0;
                starttime = System.currentTimeMillis();

            }
        }
    }
}

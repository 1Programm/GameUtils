package com.programm.lib.gameutils.game;

import java.util.function.Consumer;

public class FpsEngine {

    private float fps;
    private boolean printFps;
    private Runnable updateRunnable;
    private Runnable renderRunnable;
    private Consumer<String> logger;

    private Thread thread;
    private boolean running;

    public FpsEngine(float fps, boolean printFps, Runnable updateRunnable, Runnable renderRunnable, Consumer<String> logger){
        this.fps = fps;
        this.printFps = printFps;
        this.updateRunnable = updateRunnable;
        this.renderRunnable = renderRunnable;
        this.logger = logger;
    }

    public void start(){
        if(running) return;

        running = true;

        thread = new Thread(this::run);
        thread.start();
    }

    public void stop(){
        if(!running) return;

        running = false;
    }

    private void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = fps;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            boolean updated = false;

            while(delta >= 1){
                updateRunnable.run();
                if(printFps) updates++;
                delta--;

                updated = true;
            }

            if(updated){
                renderRunnable.run();
            }

            if(printFps) {
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    logger.accept("FPS: " + frames + " - TICKS: " + updates);
                    frames = 0;
                    updates = 0;
                }
            }
        }

        logger.accept("Engine stopped.");
    }

}

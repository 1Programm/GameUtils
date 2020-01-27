package com.programm.tests;

import com.programm.lib.gameutils.controlls.AwtKeyboard;
import com.programm.lib.gameutils.game.FpsEngine;
import com.programm.lib.gameutils.math.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class KeyboardTest {

    private static final int WIDTH = 600, HEIGHT = 500;
    private static final float SPEED = 2;

    private FpsEngine engine;
    private Canvas canvas;
    private Vector2f pos1;

    private KeyboardTest() {
        this.engine = new FpsEngine(60, true, this::update, this::render, this::logger);
        this.canvas = new Canvas();
        this.canvas.setSize(WIDTH, HEIGHT);
        this.canvas.addKeyListener(new AwtKeyboard());
        this.pos1 = new Vector2f(200, 200);

        JFrame frame = new JFrame("Keyboard Test");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        engine.start();
    }

    private void update(){
        if(AwtKeyboard.isPressed(KeyEvent.VK_ESCAPE)){
            engine.stop();
        }

        if(AwtKeyboard.W()){
            pos1.add(0, -SPEED);
        }
        if(AwtKeyboard.S()){
            pos1.add(0, SPEED);
        }
        if(AwtKeyboard.A()){
            pos1.add(-SPEED, 0);
        }
        if(AwtKeyboard.D()){
            pos1.add(SPEED, 0);
        }
    }

    private void render(){
        BufferStrategy bs = canvas.getBufferStrategy();

        if(bs == null){
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect((int)pos1.getX(), (int)pos1.getY(), 32, 32);

        g.dispose();
        bs.show();
    }

    private void logger(String toLog){
        System.out.println(toLog);
    }

    public static void main(String[] args) {
        new KeyboardTest();
    }

}

package com.programm.lib.gameutils.controlls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class AwtKeyboard extends KeyAdapter {

    private static List<Integer> keys = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(!keys.contains(keyCode)){
            keys.add(keyCode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keys.contains(keyCode)){
            keys.remove(keys.indexOf(keyCode));
        }
    }

    public static boolean isPressed(int keyCode){
        return keys.contains(keyCode);
    }

    public static boolean W(){
        return isPressed(KeyEvent.VK_W);
    }

    public static boolean A(){
        return isPressed(KeyEvent.VK_A);
    }

    public static boolean S(){
        return isPressed(KeyEvent.VK_S);
    }

    public static boolean D(){
        return isPressed(KeyEvent.VK_D);
    }
}

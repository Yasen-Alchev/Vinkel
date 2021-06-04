package com.elsys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPress implements KeyListener {
    Room room;

    KeyPress(Room room)
    {
        this.room = room;
        room.generateRoom();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_W:
                room.moveUp();
                break;
            case KeyEvent.VK_S:
                room.moveDown();
                break;
            case KeyEvent.VK_A:
                room.moveLeft();
                break;
            case KeyEvent.VK_D:
                room.moveRight();
                break;
            case KeyEvent.VK_UP:
                room.attackUp();
                break;
            case KeyEvent.VK_DOWN:
                room.attackDown();
                break;
            case KeyEvent.VK_LEFT:
                room.attackLeft();
                break;
            case KeyEvent.VK_RIGHT:
                room.attackRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

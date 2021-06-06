package com.elsys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPress implements KeyListener {
    Room room;
    private boolean inShopMenu;

    KeyPress(Room room)
    {
        this.inShopMenu = false;
        this.room = room;
        room.startingRoom();
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
                if(Room.isShop && inShopMenu) {
                    room.renderShop(-1);
                }else {
                    room.attackUp();
                }
                break;

            case KeyEvent.VK_DOWN:
                if(Room.isShop && inShopMenu) {
                    room.renderShop(1);
                }else {
                    room.attackDown();
                }
                break;

            case KeyEvent.VK_LEFT:
                room.attackLeft();
                break;

            case KeyEvent.VK_RIGHT:
                room.attackRight();
                break;

            case KeyEvent.VK_E:
                if( room.room.get(room.getCoordinate(ShopBlock.shopCoordinates.getX(),ShopBlock.shopCoordinates.getY())) instanceof ShopBlock ){
                    if(((ShopBlock)room.room.get(room.getCoordinate(ShopBlock.shopCoordinates.getX(),ShopBlock.shopCoordinates.getY()))).isUsed(room)){
                        room.renderShop(0);
                        inShopMenu = true;
                    }
                }
                break;

            case KeyEvent.VK_ESCAPE:
                if(Room.isShop && inShopMenu){
                    inShopMenu = false;
                    room.exitShop();
                }
                break;

            case KeyEvent.VK_ENTER:
                if(Room.isShop && inShopMenu){
                    room.buySelectedItem();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

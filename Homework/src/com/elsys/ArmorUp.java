package com.elsys;

import java.awt.*;

public class ArmorUp implements GameObject, Item, Passable{
    Player player;

    public ArmorUp(Player player) {
        this.player = player;
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.orange);
        g.fillRect(coordinates.getX()*30, coordinates.getY()*30, 30, 30);
    }

    @Override
    public void picked() {
        player.addArmor(5);
    }
}

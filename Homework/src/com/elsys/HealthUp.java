package com.elsys;

import java.awt.*;

public class HealthUp implements GameObject, Item{

    Player player;
    public HealthUp(Player player) {
        this.player = player;
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.pink);
        g.fillRect(coordinates.getX()*30, coordinates.getY()*30, 30, 30);
    }

    @Override
    public void picked() {
        player.addHealth(20);
    }
}

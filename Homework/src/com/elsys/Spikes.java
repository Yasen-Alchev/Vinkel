package com.elsys;

import java.awt.*;

public class Spikes implements GameObject, Passable{

    void dealDmg(Player player){
        player.takeDmg(10);
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.MAGENTA);
        g.fillRect(coordinates.getX() * 30, coordinates.getY() * 30, 30 ,30);

    }
}
package com.elsys;

import java.awt.*;

public class Orc implements Enemy{
    int hp;
    int ad;

    Orc()
    {
        this.hp = 15;
        this.ad = 20;
    }
    @Override
    public boolean takeDmg(int damage) {
        this.hp -= damage;
        if(this.hp > 0)
            return false;
        return true;
    }

    @Override
    public void dealDmg(Player player) {
        player.takeDmg(this.ad);
    }


    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.green);
        g.fillRect(coordinates.getX()*30, coordinates.getY()*30, 30, 30);
    }
}
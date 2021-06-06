package com.elsys;

import java.awt.*;

public class Orc extends Enemy{

    public final int goldDrop = 1;

    Orc(int bonusAd)
    {
        super(15, 20 + bonusAd);
    }

    @Override
    int dropGold() {
        return goldDrop;
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
        player.takeDmg(this.damage);
    }


    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.green);
        g.fillRect(coordinates.getX()*30, coordinates.getY()*30, 30, 30);
    }
}

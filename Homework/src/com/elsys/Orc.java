package com.elsys;

import java.awt.*;

public class Orc extends Enemy{
    Orc()
    {
        super(15, 20);
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

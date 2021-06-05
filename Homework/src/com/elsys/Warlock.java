package com.elsys;

import java.awt.*;

public class Warlock extends Enemy{
    Warlock()
    {
        super(20, 25);

    }

    @Override
    public boolean takeDmg(int damage) {
        this.hp -= damage;
        if(this.hp > 0)
            return false;
        return true;
    }

    public void dealDmg(Player player) {
        player.takeDmg(this.damage);
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.blue);
        g.fillRect(coordinates.getX()*30, coordinates.getY()*30, 30, 30);
    }
}

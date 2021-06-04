package com.elsys;

import java.awt.*;

public class Player implements GameObject {
    int health;
    int ad;
    int armor;

    Player()
    {
        this.health = 100;
        this.ad = 10;
        this.armor = 0;
    }

    void takeDmg(int damage)
    {
        System.out.println("armor " + this.armor);
        if(this.armor >= damage)
            return;
        this.health -= damage - this.armor;
        if(this.health <= 0)
        {
            this.health = 0;
            System.out.println("You died!");
        }
    }

    void addArmor(int armor)
    {
        this.armor += armor;
    }

    void addHealth(int health)
    {
        this.health += health;
        if(this.health > 100)
        {
            this.health = 100;
        }
    }

    void addAd(int ad)
    {
        this.ad += ad;
    }

    @Override
    public String toString()
    {
        return "Current health: " + this.health;
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.clearRect(5 * 30, 16 * 30, 32 ,30); // we do this so the hp can update
        g.setColor(Color.RED);
        g.fillRect(coordinates.getX() * 30, coordinates.getY() * 30, 30 ,30);
        g.setColor(Color.BLACK);
        g.drawString(toString(), 0, 17 * 30);
    }
}

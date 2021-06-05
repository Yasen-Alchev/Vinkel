package com.elsys;

import java.awt.*;

public class Player implements GameObject {
    int health;
    int ad;
    int armor;
    Main game;

    Player(Main game)
    {
        this.game = game;
        this.health = 100;
        this.ad = 10;
        this.armor = 0;
    }

    void takeDmg(int damage)
    {
        if(this.armor >= damage)
            return;
        this.health -= damage - this.armor;
        if(this.health <= 0)
        {
            this.health = 0;
            game.stop();
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


    public String getCurrentHealth()
    {
        return "Current health: " + this.health;
    }

    public String getCurrentArmor()
    {
        return "Current armor: " + this.armor;
    }

    public String getCurrentDamage()
    {
        return "Current damage: " + this.ad;
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.clearRect(5 * 29, 16 * 30, 100 ,100); // we do this so the hp can update
        g.setColor(Color.RED);
        g.fillRect(coordinates.getX() * 30, coordinates.getY() * 30, 30 ,30);
        g.setColor(Color.BLACK);
        g.drawString(getCurrentHealth(), 0, 17 * 30);
        g.drawString(getCurrentArmor(), 0, 18 * 30);
        g.drawString(getCurrentDamage(), 0, 19 * 30);
    }
}

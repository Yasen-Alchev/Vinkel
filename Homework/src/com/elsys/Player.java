package com.elsys;

import java.awt.*;

public class Player implements GameObject {
    int health;
    int ad;
    int armor;
    int gold;
    Main game;
    Coordinates playerCoordinates;

    Player(Main game)
    {
        this.game = game;
        this.health = 100;
        this.ad = 10;
        this.armor = 6;
        this.gold = 0;
    }

    void addGold(int gold){
        this.gold += gold;
    }

    void takeDmg(int damage)
    {
        if(this.armor >= damage)
            return;
        this.health -= damage - this.armor;
        if(this.health <= 0)
        {
            this.health = 0;
            game.stop(false);
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

    public String getCurrentGold()
    {
        return "Current gold: " + this.gold;
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        this.playerCoordinates = coordinates;
        g.clearRect(0, 460, 800 ,340); // we do this so the hp can update
        g.setColor(Color.RED);
        g.fillRect(coordinates.getX() * 30, coordinates.getY() * 30, 30 ,30);
        g.setColor(Color.BLACK);
        g.drawString(getCurrentHealth(), 0, 17 * 30);
        g.drawString(getCurrentArmor(), 0, 18 * 30);
        g.drawString(getCurrentDamage(), 0, 19 * 30);
        g.drawString(getCurrentGold(), 0, 20 * 30);
    }
}

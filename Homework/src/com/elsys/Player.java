package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player implements GameObject {
    int health;
    int ad;
    int armor;
    int maxHealth = 100;
    int gold;
    Main game;
    Coordinates playerCoordinates;
    Image img;

    Player(Main game)
    {
        this.game = game;
        this.health = maxHealth;
        this.ad = 10;
        this.armor = 6;
        this.gold = 5;
        try {
            this.img = ImageIO.read(new File("./resources/isaac.png"));
            img = img.getScaledInstance(30, 30, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addGold(int gold){
        this.gold += gold;
    }

    void upgradeMaxHealth(int addMaxHealth){
        this.maxHealth += addMaxHealth;
    }

    void getSpiked(){
        this.health -= maxHealth*5/100;
        if(this.health <= 0)
        {
            this.health = 0;
            game.stop(false);
        }
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
        if(this.health > maxHealth)
        {
            this.health = maxHealth;
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
        g.drawImage(img,coordinates.getX()*30,coordinates.getY()*30,null,null);
        g.setColor(Color.BLACK);
        g.drawString(getCurrentHealth(), 0, 17 * 30);
        g.drawString(getCurrentArmor(), 0, 18 * 30);
        g.drawString(getCurrentDamage(), 0, 19 * 30);
        g.drawString(getCurrentGold(), 0, 20 * 30);

    }
}

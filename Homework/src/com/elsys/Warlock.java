package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Warlock extends Enemy{

    public final int goldDrop = 2;
    Image img;

    Warlock(int bonusAd)
    {
        super(20, 35 + bonusAd);
        try {
            this.img = ImageIO.read(new File("./resources/warlock.png"));
            img = img.getScaledInstance(30, 30, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void dealDmg(Player player) {
        player.takeDmg(this.damage);
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.drawImage(img,coordinates.getX()*30,coordinates.getY()*30,null,null);
    }
}

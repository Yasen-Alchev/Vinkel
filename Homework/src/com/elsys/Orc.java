package com.elsys;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Orc extends Enemy{

    public final int goldDrop = 1;

    Image img;

    Orc(int bonusAd)
    {
        super(15, 20 + bonusAd);

        try {
            this.img = ImageIO.read(new File("./resources/orc.png"));
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

    @Override
    public void dealDmg(Player player) {
        player.takeDmg(this.damage);
    }


    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.drawImage(img,coordinates.getX()*30,coordinates.getY()*30,null,null);
    }
}

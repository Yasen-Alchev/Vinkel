package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HealthUp implements GameObject, Item, Passable{

    Player player;
    Image img;
    public HealthUp(Player player) {
        this.player = player;
        try {
            this.img = ImageIO.read(new File("./resources/health.png"));
            img = img.getScaledInstance(30, 30, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.drawImage(img,coordinates.getX()*30,coordinates.getY()*30,null,null);

    }

    @Override
    public void picked() {
        player.addHealth(20);
    }
}

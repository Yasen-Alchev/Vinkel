package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Spikes implements GameObject, Passable{
    Image img;
    Spikes(){
        try {
            this.img = ImageIO.read(new File("./resources/spikes.png"));
            img = img.getScaledInstance(30, 30, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dealDmg(Player player){
        player.takeDmg(10);
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.clearRect(coordinates.getX()*30,coordinates.getY()*30,30,30);
        g.setColor(Color.black);
        g.fillRect(coordinates.getX()*30,coordinates.getY()*30,30,30);
        g.drawImage(img,coordinates.getX()*30,coordinates.getY()*30,null,null);

    }
}
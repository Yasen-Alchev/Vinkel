package com.elsys;

import java.awt.*;

public class Warlock implements Enemy{
    int hp;
    int ap;
    Warlock(){
        this.hp = 20;
        this.ap = 25;

    }

    @Override
    public boolean takeDmg(int damage) {
        this.hp -= damage;
        if(this.hp > 0)
            return false;
        return true;
    }

    public void dealDmg(Player player) {
        //for(int i = 0;i<3;i++){
            //int poison = this.ap / 2;
            player.takeDmg(this.ap);
        //}
    }

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.blue);
        g.fillRect(coordinates.getX()*30, coordinates.getY()*30, 30, 30);
    }
}

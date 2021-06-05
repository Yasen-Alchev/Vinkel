package com.elsys;

import java.awt.*;

public class WarlockQuest extends Quest {

    WarlockQuest() {
        super(5);
    }

    @Override
    boolean makeProgress(Enemy enemy) {
        if(enemy instanceof Warlock)
            this.currentProgress++;
        if(this.currentProgress == this.goal)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Killed warlocks: " + this.currentProgress + "/" + this.goal;
    }

    @Override
    public void draw(Graphics2D g) {
        g.clearRect(20*29, 10, 100,30);
        g.setColor(Color.black);
        g.drawString(this.toString(), 16*30, 30);
    }
}

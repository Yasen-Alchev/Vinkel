package com.elsys;

import java.awt.*;

public class OrcQuest extends Quest {

    OrcQuest() {
        super(2);
    }

    @Override
    boolean makeProgress(Enemy enemy) {
        if(enemy instanceof Orc)
            this.currentProgress++;
        if(this.currentProgress == this.goal)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Killed orcs: " + this.currentProgress + "/" + this.goal;
    }

    @Override
    public void draw(Graphics2D g) {
        g.clearRect(20*29, 10, 50,30);
        g.setColor(Color.black);
        g.drawString(this.toString(), 16*30, 30);
    }
}

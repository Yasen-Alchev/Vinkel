package com.elsys;

import java.awt.*;

public abstract class Quest {
    int currentProgress;
    int goal;

    Quest(int goal)
    {
        this.currentProgress = 0;
        this.goal = goal;
    }

    abstract boolean makeProgress(Enemy enemy);

    @Override
    public abstract String toString();

    public abstract void draw(Graphics2D g);

}

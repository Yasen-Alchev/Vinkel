package com.elsys;

import java.awt.*;

public class EmptySpace implements GameObject, Passable
{
    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.BLACK);
        g.fillRect(coordinates.getX() * 30, coordinates.getY() * 30, 30 ,30);

    }
}

package com.elsys;

import java.awt.*;

public class Rock implements GameObject{
    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        g.setColor(Color.GRAY);
        g.fillRect(coordinates.getX() * 30, coordinates.getY() * 30, 30, 30);
    }
}

package com.elsys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    private BufferedImage image;
    public Graphics2D g;

    public void init() {
        image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

        g = image.createGraphics();

        g.setBackground(Color.white);
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
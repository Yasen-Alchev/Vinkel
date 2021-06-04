package com.elsys;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
		Random rand = new Random();
	    Room room = new Room();

		Font myFont = new Font ("TimesRoman", 1, 20);

	    JFrame frame = new JFrame("Homework Game");
	    frame.addKeyListener(new KeyPress(room));
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		canvas.init();
		canvas.g.setFont(myFont);
		frame.getContentPane().add(canvas);
		Timer timer = new Timer();

		room.dropHealth(rand.nextInt(2) + 1);
		room.dropArmor(rand.nextInt(2) + 1);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				canvas.g.setBackground(Color.WHITE);
				room.renderRoom(canvas.g);
				canvas.repaint();

			}
		}, 0, 100/3);
    }
}

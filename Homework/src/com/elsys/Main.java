package com.elsys;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	Random rand;
	Room room;
	Font myFont;
	JFrame frame;
	Canvas canvas;
	Timer timer;

	Main(){
		rand = new Random();
		room = new Room(this);
		myFont = new Font ("TimesRoman", Font.BOLD, 20);

		frame = new JFrame("Homework Game");
		frame.addKeyListener(new KeyPress(room));
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		canvas.init();
		canvas.g.setFont(myFont);

		frame.getContentPane().add(canvas);

		timer = new Timer();

		start();
	}

	void start(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				canvas.g.setBackground(Color.WHITE);
				room.renderRoom(canvas.g);
				canvas.repaint();

			}
		}, 0, 100/3);
	}

	void stop(boolean gameEnding){
		String text;
		canvas.g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		canvas.g.setColor(Color.BLACK);
		canvas.g.fillRect(0,0,800, 600);
		if(gameEnding)
		{
			text = "You win";
			canvas.g.setColor(Color.blue);
		}
		else
		{
			text = "You died";
			canvas.g.setColor(Color.red);
		}
		canvas.g.drawString(text, 400 - text.length() * 11, 300 - 5);
		canvas.repaint();
		timer.cancel();
	}

    public static void main(String[] args) {
		new Main();
    }
}

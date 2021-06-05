package com.elsys;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	public final static int BoardX = 800;
	public final static int BoardY = 800;

	Random rand;
	Room room;
	Font myFont;
	JFrame frame;
	Canvas canvas;
	Timer timer;

	Main(){
		rand = new Random();
		room = new Room(this);
		myFont = new Font ("TimesRoman", 1, 20);

		frame = new JFrame("Homework Game");
		frame.addKeyListener(new KeyPress(room));
		frame.setSize(BoardX, BoardY);
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
		canvas.g.setFont(new Font("TimesRoman", 1, 60));
		canvas.g.setColor(Color.BLACK);
		canvas.g.fillRect(0,0,BoardX, BoardY);
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
		canvas.g.drawString(text, BoardX/2 - text.length() * 15, BoardY/2 - 15);
		canvas.repaint();
		timer.cancel();
	}

	void renderShopScreen(){
		timer.cancel();
		canvas.g.setColor(Color.BLACK);
		canvas.g.fillRect(0,0,BoardX, BoardY);
		canvas.repaint();
	}

	void exitShop(){
		this.timer = new Timer();
		canvas.g.clearRect(0, 0, BoardX, BoardY);
		start();
	}

    public static void main(String[] args) {
		new Main();
    }
}

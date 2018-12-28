package fr.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Random rand = new Random();

	private Rectangle[] player = new Rectangle[2];
	private Ball ball;

	private float lastWidth, lastHeight;
	
	boolean lost = false;
	int score = 0;

	boolean debug = false;
	int frames = 0;
	
	public GamePanel(int fps, int width, int height) {
		setBackground(Color.BLACK);

		setLayout(null);
		
		lastWidth = width;
		lastHeight = height;
		
		ball = new Ball(width / 2 - width / 40, height / 2 - height / 10, width / 20, height / 20);

		player[0] = new Rectangle(0, height / 3, width / 60, (int) (height / 7.5d));
		player[1] = new Rectangle(width - 10, height / 3, width / 60, (int) (height / 7.5d));

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(1000 / fps);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (lost) {
			String s = "Partie terminée";
			String s2 = "Score : " + score;
			g.setFont(new Font("sansSerif", getWidth() / 10, getWidth() / 10));
			FontMetrics fm = g.getFontMetrics();
			g.drawString(s, getWidth() / 2 - fm.stringWidth(s) / 2, getHeight() / 3);
			g.drawString(s2, getWidth() / 2 - fm.stringWidth(s2) / 2, getHeight() / 2);
			return;
		}

		if (debug)
			frames++;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);

		g2d.setFont(new Font("sansSerif", getWidth() / 20, getWidth() / 20));

		FontMetrics fm = g.getFontMetrics();
		String str = "Score : " + score;
		g2d.drawString(str, getWidth() / 2 - fm.stringWidth(str) / 2, getHeight() / 20);

		for (Rectangle p : player) {
			p.draw(g2d);
		}

		ball.draw(g2d);
		if (!debug || frames >= 5) {
			ball.setPosition(ball.getX() + ball.getDirectionX(), ball.getY() + ball.getDirectionY());
			frames = 0;
		}

		int i = -1;

		if (ball.getX() <= player[0].width)
			i = 0;
		else if (ball.getX() + ball.getWidth() >= getWidth() - player[1].width)
			i = 1;
		if (i >= 0) {
			if ((ball.getY() + ball.getHeight() >= player[i].y
					&& ball.getY() + ball.getHeight() <= player[i].y + player[i].getHeight())
					|| (ball.getY() <= player[i].y + player[i].height && ball.getY() >= player[i].y)) {

				float hitX = player[i].getHeight() / 2 - (ball.getY() + ball.getHeight() / 2f - player[i].y);
				System.out.println("Hit : " + hitX);
				System.out.println("Ball speed : " + Math.abs(ball.directionX));
				ball.setDirection(ball.directionX * -1, -hitX / 10f);
				ball.setDirection(ball.directionX*1.1f, ball.directionY*1.1f);
				score++;
				return;
			}
			setBackground(Color.WHITE);
			lost = true;
		} else if (ball.getY() <= 0 || ball.getY() + ball.getHeight() >= getHeight()) {
			if (ball.getY() <= 0)
				ball.setPosition(ball.getX(), 1);
			else if (ball.getY() + ball.getHeight() >= getHeight())
				ball.setPosition(ball.getX(), getHeight() - ball.getHeight() - 1);
			ball.setDirection(ball.directionX, ball.directionY * -1);
		}
	}

	@Override
	public void setBounds(int x, int y, int w, int h) {
		System.out.println("called");
		ball.setPosition(ball.getX() * w / lastWidth, ball.getY() * h / lastHeight);
		ball.setSize(w / 20, h / 20);
		
		ball.setDirection(w*ball.directionX/lastWidth, h*ball.directionY/lastHeight);
		
		for (Rectangle p : player)
			p.setSize(w / 60, (int) (h / 7.5d));
		player[1].setPosition(w - player[1].width, player[1].y);
		lastWidth = w;
		lastHeight = h;
		super.setBounds(x, y, w, h);
	}

	public void keyPressed(int keyCode) {
		switch (keyCode) {
		case 90:
			changeRectangleDirection(player[0], -9);
			break;
		case 38:
			changeRectangleDirection(player[1], -9);
			break;
		case 83:
			changeRectangleDirection(player[0], 9);
			break;
		case 40:
			changeRectangleDirection(player[1], 9);
			break;
		case 68:
			debug = !debug;
			break;
		}

	}

	private void changeRectangleDirection(Rectangle player, int directionY){
		player.setDirection(0, getHeight()*directionY/600);
	}
	
	public void keyReleased(int keyCode) {
		switch (keyCode) {
		case 90:
			player[0].setDirection(0, 0);
			break;
		case 38:
			player[1].setDirection(0, 0);
			break;
		case 83:
			player[0].setDirection(0, 0);
			break;
		case 40:
			player[1].setDirection(0, 0);
			break;
		case 10:
			if(lost){
				restart();
			}
			break;
		}
	}
	
	private void restart(){
		lost = false;
		setBackground(Color.BLACK);
		int width = 600;
		int height = 600;
		ball = new Ball(width / 2 - width / 40, height / 2 - height / 10, width / 20, height / 20);

		player[0] = new Rectangle(0, height / 3, width / 60, (int) (height / 7.5d));
		player[1] = new Rectangle(width - 10, height / 3, width / 60, (int) (height / 7.5d));
		lastHeight = 600;
		lastWidth = 600;
		setBounds(getBounds());
		score = 0;
	}

}

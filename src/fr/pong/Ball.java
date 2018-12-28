package fr.pong;

import java.awt.Graphics2D;

public class Ball extends DrawableObject{
	
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		directionX = 4f;
		directionY = 4f;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.fillOval((int)x, (int)y, width, height);
		setPosition(x, y);
	}

}

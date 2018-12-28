package fr.pong;

import java.awt.Graphics2D;

public class Rectangle extends DrawableObject{

	public Rectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.fillRect((int)x, (int)y, width, height);
		setPosition(x+directionX, y+directionY);
	}

}

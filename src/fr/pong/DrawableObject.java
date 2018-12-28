package fr.pong;

import java.awt.Graphics2D;

public abstract class DrawableObject {

	float directionX, directionY;
	protected int width,height;
	protected float x,y;
	
	public DrawableObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void draw(Graphics2D g2d);
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){return x;}
	public float getY(){return y;}
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
	public void setSize(int w, int h){
		width = w;
		height = h;
	}
	
	public void setDirection(float directionX, float directionY){
		this.directionX = directionX;
		this.directionY = directionY;
	}
	
	public float getDirectionX() {
		return directionX;
	}
	
	public float getDirectionY() {
		return directionY;
	}
}

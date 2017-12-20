package com.engine.framework.math;

public class Rectangle extends Vector2{

	int width, height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(float x, float y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public int width() {
		return this.width;
	}
	
	public void width(int width) {
		this.width = width;
	}
	
	public int height() {
		return this.height;
	}
	
	public void height(int height) {
		this.height = height;
	}
	
	public void clamp(int x1, int y1, int x2, int y2) {
		if(x < x1) x = x1;
		if(x + width > x2) x = x2 - width;
		if(y < y1) y = y1;
		if(y + height > y2) y = y2 - height;
	}
	
	public boolean collidesWith(Rectangle r) {
		return (x + width >= r.x && y + height >= r.y && x <= r.x + r.width && y <= r.y + r.height);
	}
	
}

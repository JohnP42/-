package com.engine.framework.math;

public class Rectangle extends Vector2{

	public float width, height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public float top() {
		return y;
	}

	public float bottom() {
		return y + height;
	}

	public float left() {
		return x;
	}

	public float right() {
		return x + width;
	}


	public void clamp(float x1, float y1, float x2, float y2) {
		if(left() < x1) x = x1;
		if(right() > x2) x = x2 - width;
		if(top() < y1) y = y1;
		if(bottom() > y2) y = y2 - height;
	}
	
	public boolean collidesWith(Rectangle r) {
		return (x + width >= r.x && y + height >= r.y && x <= r.x + r.width && y <= r.y + r.height);
	}
	
}

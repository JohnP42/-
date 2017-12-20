package com.engine.framework.math;

public class Vector2 {

	float x, y;
	
	public Vector2() {
		this.x = 0.0f;
		this.y = 0.0f;
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float x() {
		return this.x;
	}
	
	public void x(float x) {
		this.x = x;
	}
	
	public float y() {
		return this.y;
	}
	
	public void y(float y) {
		this.y = y;
	}
	
	public void add(Vector2 v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	public void set(Vector2 v) {
		x = v.x;
		y = v.y;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
}

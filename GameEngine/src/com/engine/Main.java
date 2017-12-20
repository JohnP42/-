package com.engine;

import java.awt.Color;

import static org.lwjgl.glfw.GLFW.*;

import com.engine.framework.Game;
import com.engine.framework.Input;
import com.engine.framework.Runner;
import com.engine.framework.display.Render;
import com.engine.framework.display.Texture;
import com.engine.framework.math.Rectangle;

public class Main extends Render implements Game {

	Rectangle player = new Rectangle(50, 50, 64, 96);
	Rectangle[] src = new Rectangle[2];
	int imageIndex = 0;
	int imageCount = 1;
	Texture tex;
	
	public static void main(String[] args) {
		
		Main main = new Main();
		Runner runner = new Runner(1280, 720, "Game", Color.BLACK);
		runner.setRender(main);
		runner.setGame(main);
		runner.start();
		
	}
	
	@Override
	public void load() {
		tex = new Texture("assets/ship.png");
		src[0] = new Rectangle(0, 0, 64, 96);
		src[1] = new Rectangle(64, 0, 64, 96);
	}
	
	@Override
	public void update() {
		if(Input.keys[GLFW_KEY_LEFT]) player.add(-7, 0);
		if(Input.keys[GLFW_KEY_RIGHT])  player.add(7, 0);
		if(Input.keys[GLFW_KEY_UP]) player.add(0, -7);
		if(Input.keys[GLFW_KEY_DOWN])  player.add(0, 7);
		
		if(imageCount > 0) {
			imageCount --;
		}
		else {
			if(imageIndex == 0)
				imageIndex = 1;
			else
				imageIndex = 0;
			imageCount = 1;
		}

		player.clamp(0, 0, 1280, 720);
	}
	
	@Override
	public void draw() {
		this.drawImage(player, src[imageIndex], tex);
	}
	
}

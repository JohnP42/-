package com.engine.framework;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import com.engine.framework.display.Render;
import com.engine.framework.display.Screen;


public class Runner implements Runnable {

	long window;
	int width, height;
	boolean showMouse;
	Color bgColor;
	String title;
	Thread thread;
	Render render = new Render();
	Game game;
	
	public Runner() {
		width = 1280;
		height = 720;
		title = "Game";
		bgColor = new Color(0,128,128);
		showMouse = true;
	}

	public Runner(String title) {
		width = 1280;
		height = 720;
		this.title = title;
		bgColor = new Color(0,128,128);
		showMouse = true;
	}
	
	public Runner(int width, int height) {
		this.width = width;
		this.height = height;
		title = "Game";
		bgColor = new Color(0,128,128);
		showMouse = true;
	}

	public Runner(int width, int height, String title, Color c) {
		this.width = width;
		this.height = height;
		this.title = title;
		bgColor = c;
		showMouse = true;
	}

	public Runner(int width, int height, String title, Color c, boolean showMouse) {
		this.width = width;
		this.height = height;
		this.title = title;
		bgColor = c;
		this.showMouse = showMouse;
	}
	
	public void start() {
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public void setRender(Render render) {
		this.render = render;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {	
		
		try {
			window = Screen.init(window, width, height, title, showMouse);
			
			render.init(width, height, bgColor);
			game.load();
			
			while(Screen.running(window)) {

				game.update();
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
				render.draw();		
				glfwSwapBuffers(window);
				Input.resetScroll();
				glfwPollEvents();
			}
			
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
		} finally {
			glfwTerminate();
			glfwSetErrorCallback(null).free();
		}
	}

	
}

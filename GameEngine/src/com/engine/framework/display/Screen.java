package com.engine.framework.display;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import com.engine.framework.Input;

public class Screen {
	
	public static long init(long window, int width, int height, String title, boolean showMouse) {
		GLFWErrorCallback.createPrint(System.err).set();
		if ( !glfwInit() ) throw new IllegalStateException("Unable to initialize GLFW");
		// Configure our window
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		
		Input.callback(window);

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);

		if (showMouse)
			glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		else
			glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
		
		return window;
	}
	
	public static boolean running(long window) {
		return !glfwWindowShouldClose(window);
	}
	
}

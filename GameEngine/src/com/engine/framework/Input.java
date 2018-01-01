package com.engine.framework;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

	public static boolean[] keys = new boolean[512];
	public static boolean[] mouseButtons = new boolean[512];
	public static double mouseX;
	public static double mouseY;
	public static double scrollX;
	public static double scrollY;
	
	public static void callback(long window){
		glfwSetKeyCallback(window, (w, key, scancode, action, mods) -> {
			keys[key] = action != GLFW_RELEASE;
		});
		glfwSetMouseButtonCallback(window, (w, button, action, mods) -> {
			mouseButtons[button] = action != GLFW_RELEASE;
		});
		glfwSetCursorPosCallback(window, (w, x, y) -> {
			mouseX = x;
			mouseY = y;
		});
		glfwSetScrollCallback(window, (w, xOffset, yOffset) -> {
			scrollX += xOffset;
			scrollY += yOffset;
		});
	}

	public static void resetScroll() {
		scrollX = 0;
		scrollY = 0;
	}
	
}

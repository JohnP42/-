package com.engine.framework;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

	public static boolean[] keys = new boolean[512];
	
	public static void callback(long window){
		glfwSetKeyCallback(window, (w, key, scancode, action, mods) -> {
			keys[key] = action != GLFW_RELEASE;
		});
	}
	
}

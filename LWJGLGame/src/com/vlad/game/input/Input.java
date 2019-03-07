package com.vlad.game.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback{
	
	//Boolean array for checking if a key is pressed
	public static boolean[] keys = new boolean[65536];

	//Method called every time a key is pressed
	public void invoke(long window,  int key, int scancode, int action, int mods)
	{
		keys[key] = action == GLFW.GLFW_PRESS;
	}
	
}//ClassEnd

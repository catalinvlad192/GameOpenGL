package com.vlad.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import static java.nio.ByteBuffer.*;

import org.lwjgl.glfw.GLFWVidMode;

import com.vlad.game.input.Input;
import com.vlad.game.tick_and_render.TickThread;

public class Main{
	
	//Check if game is running
	public static boolean running = true;
	
	public static void main(String[] args) 
	{
		new Main().start();
	}

	public void start()
	{
		new TickThread().start();
	}
	
}//ClassEnd

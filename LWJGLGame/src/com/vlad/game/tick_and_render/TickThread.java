package com.vlad.game.tick_and_render;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

import com.vlad.game.Main;
import com.vlad.game.input.Input;

public class TickThread implements Runnable
{
	//Window Size
	private int width;
	private int height;
	
	//This class's thread : TICK
	private Thread tickThread;
	
	//My window
	private long window;
	
	//Constructor
	public TickThread()
	{
		width = 1280;
		height = 720;
	}
	
	//Method for initializing, then starting the game : Tick and Render threads
	public void start()
	{
		Main.running = true;
		init();
		
		new RenderThread(window).start();
		
		tickThread = new Thread(this, "Tick");
		tickThread.start();
	}
	
	//Method executed constantly while running
	public void run ()
	{	
		while(Main.running)
		{
			tick();
			
			if(glfwWindowShouldClose(window) == true)
				Main.running = false;
		}
	}
	
	//Method for logic updates, called every frame
	private void tick()
	{
		glfwPollEvents();
		
		if (Input.keys[GLFW_KEY_SPACE])
		{
			System.out.println("Space Was Pressed");
		}
		
	}
	
	//Method for initializing window
	private void init()
	{
		if (!glfwInit())
		{
			// TODO
			return;
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		window = glfwCreateWindow(width, height, "MyGame", NULL, NULL);
		
		if(window == NULL)
		{
			//TODO
			return;
		}
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width)/2, (vidmode.height() - height)/2);
		
		glfwSetKeyCallback(window, new Input());
		
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glfwShowWindow(window);
		
		
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glEnable(GL_DEPTH_TEST);
		System.out.println("OpenGL : " + glGetString(GL_VERSION));
	}
	
}//ClassEnd

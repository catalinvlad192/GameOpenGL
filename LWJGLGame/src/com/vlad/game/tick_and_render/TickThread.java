package com.vlad.game.tick_and_render;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.vlad.game.Cache;
import com.vlad.game.input.Input;

/*
 * Class responsible for the logic thread (Mutex for Cache)
 */
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
		Cache.running = true;
		init();
		
		new RenderThread(window).start();
		
		tickThread = new Thread(this, "Tick");
		tickThread.start();
	}
	
	//Method executed in the thread. Keeps calculating and updating while game is running
	public void run ()
	{	
		while(Cache.running)
		{
			tick();
			
			if(glfwWindowShouldClose(window) == true)
				Cache.running = false;
		}
	}
	
	//Method for logic updates, called every frame
	private void tick()
	{
		//Make context current then create capabilities (needed because it is used in two threads)
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
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
		
		Cache.loadAll();
	}
	
}//ClassEnd

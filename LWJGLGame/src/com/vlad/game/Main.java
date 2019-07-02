package com.vlad.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.vlad.game.input.Input;

//Class that puts everything in motion.
public class Main implements Runnable {
	
	//Window Size
	private int width = 1080;
	private int height = 720;
	
	//Thread
	private Thread thread;
	
	//My window
	private long window;
	
	public void start()
	{
		System.out.println("[Main] Game Starting!");
		Cache.running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	//Method for initializing window
	private void init()
	{
		if (!glfwInit())
		{
			System.err.println("[Main] Could not initialize GLFW!");
			return;
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		window = glfwCreateWindow(width, height, "MyGame", NULL, NULL);
		
		if(window == NULL)
		{
			System.err.println("[Main] Could not create GLFW window!");
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
		glActiveTexture(GL_TEXTURE1);
		System.out.println("[Main] OpenGL : " + glGetString(GL_VERSION));
		
		Cache.loadAll();
	}
	
	//Method executed in the thread. Keeps calculating and updating while game is running
	public void run ()
	{	
		init();
		System.out.println("[Main] Game Running!");
		
		//Run it 60 frames per second
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60;
		double delta = 0.0;
		int ticks = 0;

		while(Cache.running)
		{
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1.0)
			{
				tick();
				render();
				ticks++;
				delta--;
			}

			if(System.currentTimeMillis() - timer >1000)
			{
				timer+=1000;
				System.out.println("[Main] Ticks and Frames: " + ticks);
				ticks = 0;
			}
			
			if(glfwWindowShouldClose(window) == true)
				Cache.running = false;
		}
	}
		
	//Method for logic updates, called every frame
	private void tick()
	{		
		glfwPollEvents();
		Cache.level.tick();
		Cache.entity.tick();
		
		if (Input.keys[GLFW_KEY_SPACE])
		{
			System.out.println("Space Was Pressed");
		}
	}
		
	//Method for rendering, called every frame
	private void render()
	{	
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Cache.level.render();
		Cache.entity.render();
		
		int error = glGetError();
		if(error != GL_NO_ERROR)
		{
			System.out.println("[Main] GL Error: " + error);
		}
		
		glfwSwapBuffers(window);
	}
		

	public static void main(String[] args) 
	{
		new Main().start();
	}
}//Main


package com.vlad.game.tick_and_render;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import com.vlad.game.Cache;
import com.vlad.game.level.Level;

/*
 * Class responsible for the rendering thread (Mutex for Cache)
 */
public class RenderThread implements Runnable{

	//This class's thread : RENDER
	private Thread renderThread;
	
	//My window
	private long window;
	
	//Constructor
	public RenderThread(long window)
	{
		Cache.level = new Level();
		this.window = window;
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
	}
	
	//Method for starting Render thread
	void start()
	{
		renderThread = new Thread(this, "Render");
		renderThread.start();
	}

	//Method executed in the thread. Keeps rendering things while game is running
	public void run() {
		while(Cache.running)
		{			
			render();
		}
	}
	
	//Method for rendering, called every frame
	private void render()
	{
		//Make context current then create capabilities (needed because it is used in two threads)
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		Cache.level.render();		//Render the level
		
		int error = glGetError();
		if(error != GL_NO_ERROR)
		{
			System.out.println("[RenderThread] GL Error: " + error);
		}
		
		glfwSwapBuffers(window);
		
	}
	
}//ClassEnd

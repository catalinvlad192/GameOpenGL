package com.vlad.game.tick_and_render;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import com.vlad.game.Main;

public class RenderThread implements Runnable{

	//This class's thread : RENDER
	private Thread renderThread;
	
	//My window
	private long window;
	
	//Constructor
	public RenderThread(long window)
	{
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

	//Method executed constantly while running
	public void run() {
		while(Main.running)
		{			
			render();
		}
	}
	
	//Method for rendering, called every frame
	private void render()
	{
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window);
		
	}
	
}//ClassEnd

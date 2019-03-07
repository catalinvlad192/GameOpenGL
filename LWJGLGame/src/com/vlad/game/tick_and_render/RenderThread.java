package com.vlad.game.tick_and_render;

import static org.lwjgl.glfw.GLFW.*;

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
		glfwSwapBuffers(window);
	}
	
}//ClassEnd

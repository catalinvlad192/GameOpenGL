package com.vlad.game;

import com.vlad.game.tick_and_render.TickThread;

/*
 * Class that puts everything in motion.
 */
public class Main{
	
	/*
	 * Main function.
	 */
	public static void main(String[] args) 
	{
		new Main().start();
	}

	/*
	 * Starts the control thread which starts the render thread.
	 */
	public void start()
	{
		new TickThread().start();
	}
	
}//ClassEnd

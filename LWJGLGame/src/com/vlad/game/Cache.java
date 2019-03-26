package com.vlad.game;

import com.vlad.game.graphics.Shader;
import com.vlad.game.math.Matrix4f;

/*
 * Class full of all the static attributes needed for the game.
 * Basically an InfoModel/DataBase.
 * 
 */
public class Cache {
	
	//Background Shader
	public static Shader BG;
	
	//Check if game is running
	public static boolean running = true;
	
	
	/*
	 * Initialize and load all the attributes
	 */
	public static void loadAll()
	{
		BG = new Shader("shaders/bg.vert","shaders/bg.frag");	
		BG.enable();
		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f*9.0f/16.0f, 10.0f*9.0f/16.0f, -1.0f, 1.0f);
		BG.setUniformMat4f("pr_matrix", pr_matrix);
		BG.disable();
	}

}

package com.vlad.game;

import com.vlad.characters.Entity;
import com.vlad.game.graphics.Shader;
import com.vlad.game.level.Level;
import com.vlad.game.math.Matrix4f;

// Class full of all the static attributes needed for the game.
// Basically an InfoModel/DataBase. 
public class Cache {
	
	//Check if game is running
	public static boolean running = true;
	
	//Shader
	public static Shader ENTITY; //(soon to be a vector)
	public static Shader SKY;
	public static Shader ROCK;
	public static Shader GROUND;
	
	//Entity (soon to be a vector matching entity shaders)
	public static Entity entity;
	
	//Level
	public static Level level;
	
	//Initialize and load all the attributes
	public static void loadAll()
	{
		System.out.println("[Cache] Load elements");
		
		level = new Level();
		entity = new Entity();
		
		//Shader and fragment(colors) program for the entity (program that runs on the GPU)
		ENTITY = new Shader("shaders/entity.vert", "shaders/entity.frag");
		SKY = new Shader("shaders/bg.vert", "shaders/bg.frag");
		ROCK = new Shader("shaders/bg.vert", "shaders/bg.frag");
		GROUND = new Shader("shaders/bg.vert", "shaders/bg.frag");
		
		init();
	}
	
	private static void init()
	{
		//Projection matrix (for the starting position I think - NO)
		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f*9.0f/16.0f, 10.0f*9.0f/16.0f, -1.0f, 1.0f);
		
		//Loading sky
		SKY.enable();
		SKY.setUniformMat4f("pr_matrix", pr_matrix);
		SKY.setUniform1i("tex", 1);
		SKY.disable();
		
		//Loading rock
		ROCK.enable();
		ROCK.setUniformMat4f("pr_matrix", pr_matrix);
		ROCK.setUniform1i("tex", 1);
		ROCK.disable();
		
		//Loading ground
		GROUND.enable();
		GROUND.setUniformMat4f("pr_matrix", pr_matrix);
		GROUND.setUniform1i("tex", 1);
		GROUND.disable();
		
		//Loading entity
		ENTITY.enable();
		ENTITY.setUniformMat4f("pr_matrix", pr_matrix);
		ENTITY.setUniform1i("tex", 1);
		ENTITY.disable();
	}

}//Cache


package com.vlad.game.level;

/*
 * I don't know yet but in the begining painted half of the screen blue
 */
public class Level {
	
	//Sky rock ground objects
	private static Sky sky;
	private static Ground ground;
	private static Rock rock;
	
	//Level constructor
	public Level()
	{
		System.out.println("[Level] Starting Level");

		sky = new Sky();
		rock = new Rock();
		ground = new Ground();
	}
	
	//Update the level attributes
	public void tick()
	{
		sky.tick();
		rock.tick();
		ground.tick();
	}
	
	//Enables Backgrownd, renders then disables background
	public void render()
	{
		sky.render();
		rock.render();
		ground.render();
	}

}//Level


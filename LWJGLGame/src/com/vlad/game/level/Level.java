package com.vlad.game.level;

import com.vlad.game.Cache;
import com.vlad.game.graphics.VertexArray;

/*
 * I don't know yet but in the begining painted half of the screen blue
 */
public class Level {
	
	//Attribute to prepare background rendering
	private VertexArray background;
	
	/*
	 * Constructor of the Level
	 */
	public Level()
	{
		//Vertices for a square on half of the screen
		float[] vertices = new float[] {
				-10.0f, -10.0f*9.0f/16.0f, 0.0f,
				-10.0f,  10.0f*9.0f/16.0f, 0.0f,
				  0.0f,  10.0f*9.0f/16.0f, 0.0f,
				  0.0f, -10.0f*9.0f/16.0f, 0.0f
		};
		
		//Indices to draw two triangles using vertices
		byte[] indices = new byte[] {
				0, 1, 2,
				2, 3, 0
		};
		
		//
		float[] tcs/*TextureCoordinates*/ = new float[] {
				0, 1, 
				0, 0,
				1, 0,
				1, 1
		};
		
		//Create the background vertexArray based on vertices, indices and textureCoordinates
		background = new VertexArray(vertices, indices, tcs);
	}
	
	//Enables Backgrownd, renders then disables background
	public void render() {
		Cache.BG.enable();
		background.render();
		Cache.BG.disable();
	}

}

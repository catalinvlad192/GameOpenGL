package com.vlad.game.level;

import com.vlad.game.Cache;
import com.vlad.game.graphics.Texture;
import com.vlad.game.graphics.VertexArray;
import com.vlad.game.math.Matrix4f;
import com.vlad.game.math.Vector3f;

/*
 * I don't know yet but in the begining painted half of the screen blue
 */
public class Level {
	
	//Attribute to prepare background rendering
	private VertexArray background;
	
	//Background
	private Texture bgTexture;
	
	//Scrolling on x axis (should be updated according to the player's position)
	private int xScroll = 0;
	
	private int mapMove = 0;
	
	/*
	 * Constructor of the Level
	 */
	public Level()
	{
		//Vertices for a square on half of the screen
		float[] vertices = new float[] {
				-10.0f, 3.0f*9.0f/16.0f, 0.0f,
				-10.0f,  10.0f*9.0f/16.0f, 0.0f,
				  0.0f,  10.0f*9.0f/16.0f, 0.0f,
				  0.0f, 3.0f*9.0f/16.0f, 0.0f
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
		
		//Create the texture for the background
		bgTexture = new Texture("res/map/sky.png");
	}
	
	//Update the level attributes
	public void tick()
	{
		xScroll--;
		if(xScroll % 300 == 0) mapMove++;
	}
	
	//Enables Backgrownd, renders then disables background
	public void render()
	{
		bgTexture.bind();
		Cache.BG.enable();
		background.bind();
		for(int i = mapMove; i < mapMove + 4; i++)
		{
			Cache.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(i*10 + xScroll, 0.0f, 0.0f)));
			background.draw();
		}
		background.render();
		Cache.BG.disable();
		bgTexture.unbind();
	}

}

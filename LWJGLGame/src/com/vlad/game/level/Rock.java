package com.vlad.game.level;

import com.vlad.game.Cache;
import com.vlad.game.graphics.Texture;
import com.vlad.game.graphics.VertexArray;
import com.vlad.game.math.Matrix4f;
import com.vlad.game.math.Vector3f;

/*
 * I don't know yet but in the begining painted half of the screen blue
 */
public class Rock {
	
	//Attribute (Mesh)(hitbox)
	private VertexArray mesh;
	
	//Background texture(image)
	private Texture texture;
	
	//Level constructor
	public Rock()
	{
		System.out.println("[Rock] Drawing rock");
		
		//Vertices for a square on half of the screen
		float[] vertices = new float[] {
				-12.0f, -2.0f*9.0f/16.0f, 0.0f,
				-12.0f,  3.0f*9.0f/16.0f, 0.0f,
				 10.0f,  3.0f*9.0f/16.0f, 0.0f,
				 10.0f, -2.0f*9.0f/16.0f, 0.0f
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
		
		//Create the background vertexArray based on vertices, indices and textureCoordinates (mesh)(hitbox)
		mesh = new VertexArray(vertices, indices, tcs);
		
		//Create the texture for the background
		texture = new Texture("res/map/rock.png");
	}
	
	//Update the level attributes
	public void tick()
	{

	}
	
	//Enables Backgrownd, renders then disables background
	public void render()
	{
		texture.bind();
		Cache.ROCK.enable();
		mesh.bind();
		Cache.ROCK.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(1.0f, 0.0f, 0.0f)));
		mesh.render();
		Cache.ROCK.disable();
		texture.unbind();
		
		Cache.ENTITY.enable();
	}

}//Rock

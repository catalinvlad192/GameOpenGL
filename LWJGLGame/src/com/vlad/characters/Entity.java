package com.vlad.characters;

import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL11.*;

import com.vlad.game.Cache;
import com.vlad.game.graphics.Texture;
import com.vlad.game.graphics.VertexArray;
import com.vlad.game.input.Input;
import com.vlad.game.math.Matrix4f;
import com.vlad.game.math.Vector3f;

public class Entity 
{
	//Size of the entity
	private float size = 1.0f;
	
	//hitbox
	private VertexArray mesh;
	
	//Actual image
	private Texture texture;
	
	//Entity position
	private Vector3f position = new Vector3f();
	
	public Entity()
	{
		System.out.println("[Entity] Entity created: <attributes>");
		
		//Vertices for a square on half of the screen
		float[] vertices = new float[] {
				-size / 5.0f, -size / 2.0f, 0.2f,
				-size / 5.0f,  size / 2.0f, 0.2f,
				size / 2.0f,  size / 2.0f, 0.2f,
				size / 2.0f, -size / 2.0f, 0.2f
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
		mesh = new VertexArray(vertices, indices, tcs);
		
		//Create the texture for the entity(might add path as a constructor parameter + character type ((Entity factory)))
		texture = new Texture("res/Itachi/ItachiStanding1.png");
	}
	
	//Tick function
	public void tick()
	{
		if(Input.keys[GLFW_KEY_W]) position.y++;
		if(Input.keys[GLFW_KEY_A]) position.x--;
		if(Input.keys[GLFW_KEY_S]) position.y--;
		if(Input.keys[GLFW_KEY_D]) position.x++;
	}
	
	//Render function
	public void render()
	{
		Cache.ENTITY.enable();
		mesh.bind();
		
		Cache.ENTITY.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		
		texture.bind();
		mesh.draw();
		mesh.render();
		
		Cache.ENTITY.disable();
		texture.unbind();
	}
}//Entity


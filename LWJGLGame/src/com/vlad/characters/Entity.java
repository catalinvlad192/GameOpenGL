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
	//Timer for various actions
	private int timer_;

	//Size of the entity
	private float size_ = 1.0f;
	
	//hitbox
	private VertexArray mesh_;
	
	//Actual image
	private Texture standTexture1_, standTexture2_;
	
	//Entity position
	private Vector3f position_ = new Vector3f();
	
	public Entity()
	{
		System.out.println("[Entity] Entity created: <attributes>");
		
		timer_ = 0;

		//Vertices for a square on half of the screen
		float[] vertices = new float[] {
				-size_ / 5.0f, -size_ / 2.0f, 0.2f,
				-size_ / 5.0f,  size_ / 2.0f, 0.2f,
				size_ / 2.0f,  size_ / 2.0f, 0.2f,
				size_ / 2.0f, -size_ / 2.0f, 0.2f
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
		mesh_ = new VertexArray(vertices, indices, tcs);
		
		//Create the texture for the entity(might add path as a constructor parameter + character type ((Entity factory)))
		standTexture1_ = new Texture("res/Itachi/ItachiStanding1.png");
		standTexture2_ = new Texture("res/Itachi/ItachiStanding3.png");
	}
	
	//Tick function
	public void tick()
	{
		timer_ = (timer_+1)%60;

		if(Input.keys[GLFW_KEY_W]) position_.y+=0.02f;
		if(Input.keys[GLFW_KEY_A]) position_.x-=0.02f;
		if(Input.keys[GLFW_KEY_S]) position_.y-=0.02f;
		if(Input.keys[GLFW_KEY_D]) position_.x+=0.02f;
	}
	
	//Render function
	public void render()
	{
		Cache.ENTITY.enable();
		mesh_.bind();
		
		Cache.ENTITY.setUniformMat4f("ml_matrix", Matrix4f.translate(position_));
		
		if(timer_ <= 30)
		{
			standTexture1_.bind();
		}
		else
		{
			standTexture2_.bind();
		}
		
		mesh_.render();
		
		Cache.ENTITY.disable();
		standTexture1_.unbind();
	}
}//Entity

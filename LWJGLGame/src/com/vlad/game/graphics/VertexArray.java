package com.vlad.game.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.vlad.game.utils.BufferUtils;

/*
 * Class responsible of holding an array of vertices [points/vertex(es)]
 */
public class VertexArray 
{
	//Attribures (ID's)
	private int vao; //vertexArrayObject 
	private int vbo; //vertexBufferObject
	private int ibo; //indexBufferObject
	private int tcbo; //textureCoordinatesBufferObject
	private int count;

	/*
	 * Constructor takes vertices (points), indices (which tells how to connect the vertices), and textureCoordinates
	 * and adds them into OpenGL
	 */
	public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates)
	{
		count = indices.length;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);
		
		tcbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.TCOORD_ATTRIB);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindVertexArray(0);
	}
	
	// Binds the VertexArray to OpenGL
	public void bind() {
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
	}
	
	//Unbinds
	public void unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	//Draws the form based on vertices, index and texture coordinates
	public void draw()
	{
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
	}
	
	//Render
	public void render()
	{
		bind();
		draw();
	}
}



package com.vlad.game.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import com.vlad.game.math.Matrix4f;
import com.vlad.game.math.Vector3f;
import com.vlad.game.utils.ShaderUtils;

/*
 * Class responsible of holding and creating shader programs that run on the GPU.
 */
public class Shader {
	
	//Attributes
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1; //Texture Coordinates
	
	//Enable or disable all shaders
	public static boolean enabled = false;
	
	//Current shader's ID
	private final int ID;
	
	
	//Location cache for holding variable name and the location integer
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();

	/*
	 * The constructor needs two paths:
	 * 		-one to the shader program (vertex) that manages the vertices
	 * 		-one to the shader program (fragment) that manages colors
	 */
	public Shader(String vertex, String fragment)
	{
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	/*
	 * Returns the uniform location of the shader based on name
	 * Add location in the locationCache if not found
	 */
	public int getUniform(String name)
	{
		if(locationCache.containsKey(name))
			return locationCache.get(name);
		
		int result =  glGetUniformLocation(ID, name);
		
		if(result == -1)
			System.err.println("[Shader] Could not find uniform variable '" + name + "'!");
		else
			locationCache.put(name, result);
			
		return result;
	}
	
	//Set int location of the shader
	public void setUniform1i(String name, int value)
	{
		if(!Shader.enabled) enable();
		glUniform1i(getUniform(name), value);
	}
	
	//Set float location of the shader
	public void setUniform1f(String name, float value)
	{
		if(!Shader.enabled) enable();
		glUniform1f(getUniform(name), value);
	}
	
	//Set double float location of the shader
	public void setUniform2f(String name, float x, float y)
	{
		if(!Shader.enabled) enable();
		glUniform2f(getUniform(name), x, y);
	}
	
	//Set triple float location of the shader
	public void setUniform3f(String name, Vector3f vector)
	{
		if(!Shader.enabled) enable();
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	//Set matrix location of the shader
	public void setUniformMat4f(String name, Matrix4f matrix)
	{
		if(!Shader.enabled) enable();
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	//Enable Shader program so it can be run
	public void enable()
	{
		glUseProgram(ID);
		Shader.enabled = true;
	}
	
	//Disable the Shader program
	public void disable()
	{
		glUseProgram(0);
		Shader.enabled = false;
	}
}

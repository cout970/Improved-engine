package com.cout970.engine.shader

import com.cout970.engine.log.EngineLogger
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import java.io.Closeable

/**
 * Created by cout970 on 30/07/2016.
 */
/**
 * This class represents a shader program in OpenGL
 */
abstract class ShaderProgram : Closeable {

    val programID: Int
    val vertexID: Int
    val fragmentID: Int

    val variables = mutableListOf<UniformVariable>()

    constructor(vertex: String, fragment: String) {
        vertexID = compile(GL20.GL_VERTEX_SHADER, vertex)
        fragmentID = compile(GL20.GL_FRAGMENT_SHADER, fragment)
        programID = GL20.glCreateProgram()
        GL20.glAttachShader(programID, vertexID)
        GL20.glAttachShader(programID, fragmentID)
        bindAttributes()
        GL20.glLinkProgram(programID)
        GL20.glValidateProgram(programID)
        loadUniformLocations()
    }

    private fun compile(type: Int, code: String): Int {
        val shader = GL20.glCreateShader(type)
        GL20.glShaderSource(shader, code)
        GL20.glCompileShader(shader)
        if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            val name = if(type == GL20.GL_VERTEX_SHADER) "vertex" else "fragment"
            val lines = code.split("\n")
            for (i in 0 until lines.size){
                println("$i | ${lines[i]}")
            }
            System.out.flush()
            throw IllegalStateException("Error trying to compile "+ name  +" shader: " + GL20.glGetShaderInfoLog(shader, 1000))
        }
        return shader
    }

    /**
     * Starts the program, if other program is active, this will stop it
     */
    fun start() = GL20.glUseProgram(programID)

    /**
     * End the shader program
     */
    fun stop() = GL20.glUseProgram(0)


    abstract fun bindAttributes()

    /**
     * Binds an attribute in the VAO to a 'in' variable in the shader
     */
    protected fun bindAttribute(slot: Int, name: String) {
        GL20.glBindAttribLocation(programID, slot, name)
    }

    /**
     * Loads all the uniforms locations
     */
    abstract fun loadUniformLocations()

    /**
     * Creates an uniform variable representing a variable in the shader
     */
    protected fun createUniformVariable(name: String): UniformVariable {
        val id = GL20.glGetUniformLocation(programID, name)
        if (id == -1) {
            EngineLogger.error("Error trying to get a uniform variable location for: "+ name)
        }
        val uni = UniformVariable(id)
        variables.add(uni)
        return uni
    }

    /**
     * Creates an uniform variable representing an array
     */
    protected fun createUniformVariableArray(name: String): UniformVariable.UniformVariableArray {
        val id = GL20.glGetUniformLocation(programID, name)
        if (id == -1) {
            EngineLogger.error("Error trying to get a uniform variable location for: "+ name)
        }
        val uni = UniformVariable.UniformVariableArray(id)
        variables.add(uni)
        return uni
    }

    /**
     * Detaches the shader program and deletes the shaders
     */
    override fun close() {
        stop()
        GL20.glDetachShader(programID, vertexID)
        GL20.glDetachShader(programID, fragmentID)
        GL20.glDeleteShader(vertexID)
        GL20.glDeleteShader(fragmentID)
        GL20.glDeleteProgram(programID)
    }
}
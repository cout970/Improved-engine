package com.cout970.engine

import com.cout970.engine.util.EngineLogger
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL

/**
 * Created by cout970 on 25/07/2016.
 */
object Loader {

    fun init(){
        if (!GLFW.glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }
        EngineLogger.init()
        EngineLogger.info("=======================================================================================================")
        EngineLogger.info("Starting Logger")
    }

    fun initOpenGL(){
        GL.createCapabilities()
    }

    fun close() {
        GLFW.glfwTerminate()
        EngineLogger.info("Stopping Logger")
        EngineLogger.info("=======================================================================================================")
    }
}
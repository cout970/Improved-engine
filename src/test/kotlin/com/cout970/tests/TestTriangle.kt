package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.renderer.AbstractRenderer
import com.cout970.engine.renderer.VaoFormatPC
import com.cout970.engine.shader.ShaderProgram
import com.cout970.engine.tessellator.Tessellator
import com.cout970.engine.tessellator.VAO
import com.cout970.engine.util.GameLoop
import com.cout970.engine.util.math.vec2Of
import com.cout970.engine.window.IWindow
import com.cout970.engine.window.WindowBuilder
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30

/**
 * Created by cout970 on 11/08/2016.
 */

fun main(args: Array<String>) {
    Loader.init()
    TestTriangle.create()
    Loader.initOpenGL()
    TestTriangle.run()
    Loader.close()
}

object TestTriangle {

    lateinit var window: IWindow
    lateinit var gameLoop: GameLoop
    lateinit var triangleVao: VAO
    lateinit var renderer: Renderer

    fun create() {
        window = WindowBuilder.build {
            loadDefaultProperties()
            title = "TestTriangle"
            size = vec2Of(500, 500)
        }.apply { center() }
    }

    fun run() {
        //renderer and shader creation
        renderer = Renderer()
        //triangle model creation
        Tessellator.setFormat(VaoFormatPC.INSTANCE)
        triangleVao = Tessellator.compile {

            set(0, -0.8, -0.8).set(1, 1, 0, 0).endVertex()
            set(0,  0.8, -0.8).set(1, 0, 1, 0).endVertex()
            set(0,    0,  0.5).set(1, 0, 0, 1).endVertex()
        }
        //game loop creation
        gameLoop = GameLoop {

            //background repaint
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            //enables smooth color blending
            glEnable(GL30.GL_FRAMEBUFFER_SRGB)

            //model rendering
            renderer.run {
                preRender()
                render(triangleVao)
                postRender()
            }

            //updates the window image
            window.swapBuffers()
            //updates the image scale in the window
            window.setViewport(window.box)
            //call the glfw callbacks and fire the events to the registered windows
            EventManager.pollEvents()
            //if the close button has been pressed the program stops
            if (window.shouldClose()) gameLoop.stop()
        }
        //start
        gameLoop.start()
    }

    class Renderer : AbstractRenderer() {

        val shader = Shader()

        override fun preRender() = shader.start()

        override fun postRender() = shader.stop()

    }

    class Shader() : ShaderProgram(vertex_shader, fragment_shader) {

        override fun bindAttributes() {
            bindAttribute(0, "pos")
            bindAttribute(1, "color")
        }

        override fun loadUniformLocations() {}
    }

    val vertex_shader =
"""
#version 400 core

in vec2 pos;
in vec3 color;

out vec3 outColor;

void main(){
    gl_Position = vec4(pos, 0.0, 1.0);
    outColor = color;
}
"""

    val fragment_shader =
"""
#version 400 core

in vec3 outColor;

out vec4 pixel;

void main(){
    pixel = vec4(outColor, 1.0);
}
"""

}
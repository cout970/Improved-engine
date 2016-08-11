package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.renderer.AbstractRenderer
import com.cout970.engine.renderer.VaoFormatPTN
import com.cout970.engine.resource.EngineDomain
import com.cout970.engine.resource.Resource
import com.cout970.engine.shader.ShaderProgram
import com.cout970.engine.tessellator.Tessellator
import com.cout970.engine.tessellator.VAO
import com.cout970.engine.texture.Texture
import com.cout970.engine.texture.TextureLoader
import com.cout970.engine.util.GameLoop
import com.cout970.engine.util.math.vec2Of
import com.cout970.engine.window.IWindow
import com.cout970.engine.window.WindowBuilder
import org.lwjgl.opengl.GL11

/**
 * Created by cout970 on 11/08/2016.
 */


fun main(args: Array<String>) {
    Loader.init()
    TestTexture.create()
    Loader.initOpenGL()
    TestTexture.run()
    Loader.close()
}

object TestTexture {

    lateinit var window: IWindow
    lateinit var gameLoop: GameLoop
    lateinit var quadVao: VAO
    lateinit var quadTexture: Texture
    lateinit var renderer: Renderer

    fun create() {
        window = WindowBuilder.build {
            loadDefaultProperties()
            title = "TestTexture"
            size = vec2Of(500, 500)
        }.apply { center() }
    }

    fun run() {
        //renderer and shader creation
        renderer = Renderer()
        //quad model creation
        Tessellator.setFormat(VaoFormatPTN.INSTANCE)
        quadVao = Tessellator.compile(GL11.GL_QUADS) {

            set(0, -0.5, -0.5, 0).set(1, 0, 1).set(2, 0, 0, 1).endVertex()
            set(0, 0.5, -0.5, 0).set(1, 1, 1).set(2, 0, 0, 1).endVertex()
            set(0, 0.5, 0.5, 0).set(1, 1, 0).set(2, 0, 0, 1).endVertex()
            set(0, -0.5, 0.5, 0).set(1, 0, 0).set(2, 0, 0, 1).endVertex()
        }
        //texture creation
        val textureData = TextureLoader.loadTexture(Resource(EngineDomain, "textures/tests.png"))
        quadTexture = TextureLoader.uploadTexture2D(textureData)
        quadTexture.magFilter = Texture.PIXELATED

        //game loop creation
        gameLoop = GameLoop {

            //background repaint
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            //enables smooth color blending
//            GL11.glEnable(GL30.GL_FRAMEBUFFER_SRGB)

            //model rendering
            renderer.run {
                preRender()
                render(quadVao)
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
            bindAttribute(1, "tex")
            bindAttribute(2, "norm")
        }

        override fun loadUniformLocations() {
        }
    }

    val vertex_shader =
            """
#version 400 core

in vec3 pos;
in vec2 tex;
in vec3 norm;

out vec2 coords;

void main(){
    gl_Position = vec4(pos, 1.0);
    coords = tex;
}
"""

    val fragment_shader =
            """
#version 400 core

in vec2 coords;

out vec4 pixel;

uniform sampler2D sampler;

void main(){
    pixel = texture(sampler, coords);
}
"""

}
package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.renderer.Renderer
import com.cout970.engine.resource.EngineDomain
import com.cout970.engine.resource.Resource
import com.cout970.engine.shader.ShaderProgram
import com.cout970.engine.tessellator.Tessellator
import com.cout970.engine.tessellator.VAO
import com.cout970.engine.texture.Texture
import com.cout970.engine.texture.TextureLoader
import com.cout970.engine.window.WindowBuilder
import org.joml.Vector2d
import org.lwjgl.opengl.GL11

/**
 * Created by cout970 on 30/07/2016.
 */

fun main(args: Array<String>) {

    val builder = WindowBuilder()
    builder.pos = Vector2d(20.0, 30.0)
    builder.size = Vector2d(500.0, 500.0)
    builder.title = "Tile 1"

    builder.loadDefaultProperties()

    Loader.init()

    val window = builder.create()

    Loader.initOpenGL()

    val res = Resource(EngineDomain, "test_texture.png")
    val textureData = TextureLoader.loadTexture(res)
    val texture = TextureLoader.uploadTexture2D(textureData)

    texture.minFilter = GL11.GL_NEAREST
    texture.magFilter = GL11.GL_NEAREST

    texture.wrapS = Texture.REPEAT
    texture.wrapT = Texture.REPEAT

    val renderer = Renderer()
    val vao = VaoTest.createVao()

    while (true) {

        texture.bind()
        VaoTest.BasicShader.start()
        VaoTest.drawVao(renderer)
//        renderer.render(vao)
        VaoTest.BasicShader.stop()

        GL11.glFlush()
        //actualizacion de frame
        window.swapBuffers()
        //gestion de eventos como el teclado
        EventManager.pollEvents()
        if (window.shouldClose()) break
    }
    Loader.close()
}

object VaoTest {

    fun drawOld() {
        val a = 0.5
        val b = -0.5
        GL11.glBegin(GL11.GL_QUADS)
        GL11.glTexCoord2d(0.0, -1.0)
        GL11.glVertex3d(b, b, 0.0)

        GL11.glTexCoord2d(1.0, 0.0)
        GL11.glVertex3d(a, b, 0.0)

        GL11.glTexCoord2d(1.0, 1.0)
        GL11.glVertex3d(a, a, 0.0)

        GL11.glTexCoord2d(0.0, 1.0)
        GL11.glVertex3d(b, a, 1.0)
        GL11.glEnd()
    }

    fun createVao(): VAO {

        val t = Tessellator

        val start = -0.5f
        val end__ = 0.5f

        t.startCompilation(GL11.GL_QUADS)
        t.set(0, start, start, 0.0).set(1, 0f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, start, 0.0).set(1, 1f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, end__, 0.0).set(1, 1f, 0f).set(2, 0, 1, 0).endVertex()
        t.set(0, start, end__, 0.0).set(1, 0f, 0f).set(2, 0, 1, 0).endVertex()
        return t.endCompilation()
    }

    fun drawVao(renderer: Renderer) {

        val t = Tessellator

        val start = -0.5f
        val end__ = 0.5f

        t.startDrawing(GL11.GL_QUADS)
        t.set(0, start, start, 0.0).set(1, 0f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, start, 0.0).set(1, 1f, 1f).set(2, 0, 1, 0).endVertex()
        t.set(0, end__, end__, 0.0).set(1, 1f, 0f).set(2, 0, 1, 0).endVertex()
        t.set(0, start, end__, 0.0).set(1, 0f, 0f).set(2, 0, 1, 0).endVertex()
        t.endDrawing()
        t.draw(renderer)
    }

    var vertexCode =
            """
#version 400 core

in vec3 pos;
in vec2 tex;
in vec3 norm;

out vec2 tex_coord;
out vec3 norm_vec;

void main(void){
    gl_Position = vec4(pos, 1.0);
    tex_coord = tex;
}
"""

    var fragmentCode =
            """
#version 400 core

in vec2 tex_coord;

out vec4 color;

uniform sampler2D textureSampler;

void main(void){
    color = texture(textureSampler, tex_coord);
    float grey = (color.r + color.g + color.b) / 3;
    color = vec4(grey, grey, grey, 1.0);
}
"""

    object BasicShader : ShaderProgram(vertexCode, fragmentCode) {

        override fun bindAttributes() {
            bindAttribute(0, "pos")
            bindAttribute(1, "tex")
            bindAttribute(2, "norm")
        }

        override fun loadUniformLocations() {
        }
    }
}
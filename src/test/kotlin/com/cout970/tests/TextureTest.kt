package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.resource.EngineDomain
import com.cout970.engine.resource.Resource
import com.cout970.engine.texture.Texture
import com.cout970.engine.texture.TextureLoader
import com.cout970.engine.window.WindowBuilder
import org.joml.Vector2d
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*

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

    while (true) {

        glPushMatrix()
//        val s = 0.1
//        glScaled(s, s, s)
        drawTexture(texture)
        glPopMatrix()

        //actualizacion de frame
        window.swapBuffers()
        //gestion de eventos como el teclado
        EventManager.pollEvents()
        if (window.shouldClose()) break
    }

    Loader.close()
}

fun drawTexture(texture: Texture) {
    texture.bind()
    glBegin(GL_QUADS)
    glTexCoord2d(0.0, 0.0)
    glVertex3d(-1.0, -1.0, 0.0)

    glTexCoord2d(10.0, 0.0)
    glVertex3d(1.0, -1.0, 0.0)

    glTexCoord2d(10.0, 10.0)
    glVertex3d(1.0, 1.0, 0.0)

    glTexCoord2d(0.0, 10.0)
    glVertex3d(-1.0, 1.0, 0.0)
    glEnd()
    Texture.unbind()
}

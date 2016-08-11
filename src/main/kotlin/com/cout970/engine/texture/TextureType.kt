package com.cout970.engine.texture

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13

/**
 * Created by cout970 on 30/07/2016.
 */

enum class TextureType(
        val glID: Int
) {
    TEXTURE_1D(GL11.GL_TEXTURE_1D),
    TEXTURE_2D(GL11.GL_TEXTURE_2D),
    TEXTURE_CUBE(GL13.GL_TEXTURE_CUBE_MAP)
}
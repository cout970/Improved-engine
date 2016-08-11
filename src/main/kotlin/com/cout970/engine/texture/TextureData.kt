package com.cout970.engine.texture

import com.cout970.engine.resource.Resource
import com.cout970.engine.util.math.Vector2
import org.lwjgl.opengl.GL11
import java.nio.ByteBuffer

/**
 * Created by cout970 on 29/07/2016.
 */
/**
 * This class stores a bitmap of a texture, the size, the pixel format, the origin of the texture and the
 * output format used when this texture is uploaded to OpenGL
 */
data class TextureData(
        //The texture stored as a bitmap
        val bitMap: ByteBuffer,
        //The size of the texture, (width, height)
        val size: Vector2,
        //the format of the bitMap, for example GL11.GL_RGB or GL11.GL_RGBA
        val format: Int,
        //The source of this texture
        val resource: Resource,
        //The format use to upload the texture to OpenGL, for example GL11.GL_RGB or GL11.GL_ALPHA
        val outputFormat: Int = GL11.GL_RGB
)
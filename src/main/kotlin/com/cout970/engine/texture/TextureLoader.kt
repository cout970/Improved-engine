package com.cout970.engine.texture

import com.cout970.engine.resource.Resource
import com.cout970.engine.util.math.xi
import com.cout970.engine.util.math.yi
import com.cout970.engine.util.toByteBuffer
import org.joml.Vector2d
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL13
import org.lwjgl.stb.STBImage
import java.io.InputStream

/**
 * Created by cout970 on 29/07/2016.
 */
object TextureLoader {

    fun loadTexture(res: Resource): TextureData = loadTexture(res.inputStream, res)

    fun loadTexture(data: InputStream, res: Resource): TextureData {
        val image = data.toByteBuffer()
        image.flip()

        if (STBImage.stbi_info_from_memory(image, IntArray(1), IntArray(1), IntArray(1)) == 0) {
            throw IllegalStateException("Failed to load image information for $res, Error: " + STBImage.stbi_failure_reason())
        }

        val width = BufferUtils.createIntBuffer(1)
        val height = BufferUtils.createIntBuffer(1)
        val byteDepth = BufferUtils.createIntBuffer(1)

        val bitmap = STBImage.stbi_load_from_memory(image, width, height, byteDepth, 3) ?: throw RuntimeException("Failed to load image at $res, Error: " + STBImage.stbi_failure_reason())
        return TextureData(bitmap, Vector2d(width[0].toDouble(), height[0].toDouble()), byteDepth[0], res)
    }

    fun uploadTexture1D(data: TextureData): Texture {
        val id = glGenTextures()
        glEnable(TextureType.TEXTURE_1D.glID)
        glBindTexture(TextureType.TEXTURE_1D.glID, id)

        val format = if (data.byteDepth == 3) GL_RGB else GL_RGBA
        glTexImage1D(TextureType.TEXTURE_1D.glID, 0, format, data.size.xi, 0, GL_RGB, GL_UNSIGNED_BYTE, data.bitMap)
        return Texture(id, data.size.xi, data.size.yi, data.resource, TextureType.TEXTURE_1D)
    }

    fun uploadTexture2D(data: TextureData): Texture {
        val id = glGenTextures()
        glEnable(TextureType.TEXTURE_2D.glID)
        glBindTexture(TextureType.TEXTURE_2D.glID, id)

        val format = if (data.byteDepth == 3) GL_RGB else GL_RGBA
        glTexImage2D(TextureType.TEXTURE_2D.glID, 0, format, data.size.xi, data.size.yi, 0, GL_RGB, GL_UNSIGNED_BYTE, data.bitMap)

        return Texture(id, data.size.xi, data.size.yi, data.resource, TextureType.TEXTURE_2D)
    }

    fun uploadTextureCubeMap(datas: List<TextureData>): Texture {
        val id = glGenTextures()
        glEnable(TextureType.TEXTURE_CUBE.glID)
        glBindTexture(TextureType.TEXTURE_CUBE.glID, id)

        val format = if (datas[0].byteDepth == 3) GL_RGB else GL_RGBA
        for (i in 0..5) {
            val data = datas[i]
            glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, format, data.size.xi, data.size.yi, 0, GL_RGB, GL_UNSIGNED_BYTE, data.bitMap)
        }

        return Texture(id, datas[0].size.xi, datas[0].size.yi, datas[0].resource, TextureType.TEXTURE_CUBE)
    }
}
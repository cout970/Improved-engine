package com.cout970.engine.texture

import com.cout970.engine.resource.Resource
import com.cout970.engine.util.math.Vector2
import com.cout970.engine.util.math.vec2Of
import com.cout970.engine.util.extensions.toByteBuffer
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL13
import org.lwjgl.stb.STBImage
import org.lwjgl.stb.STBTTBakedChar
import org.lwjgl.stb.STBTruetype
import java.io.InputStream

/**
 * Created by cout970 on 29/07/2016.
 */
/**
 * Basic class to Load texture files and upload the textures to OpenGL
 */
object TextureLoader {

    /**
     * Loads a Texture from a resource
     */
    fun loadTexture(res: Resource): TextureData = loadTexture(res.inputStream, res)

    /**
     * Loads a Texture from a InputStream, resource is used to store the location of the image
     */
    fun loadTexture(data: InputStream, res: Resource): TextureData {
        val image = data.toByteBuffer().apply { flip() }

        if (STBImage.stbi_info_from_memory(image, IntArray(1), IntArray(1), IntArray(1)) == 0) {
            throw IllegalStateException("Failed to load image information for $res, Error: " + STBImage.stbi_failure_reason())
        }

        val width = BufferUtils.createIntBuffer(1)
        val height = BufferUtils.createIntBuffer(1)
        val byteDepth = BufferUtils.createIntBuffer(1)

        val bitmap = STBImage.stbi_load_from_memory(image, width, height, byteDepth, 3) ?:
                throw IllegalStateException("Failed to load image at $res, Error: " + STBImage.stbi_failure_reason())

        return TextureData(bitmap, vec2Of(width[0], height[0]), if (byteDepth[0] == 3) GL_RGB else GL_RGBA, res)
    }

    /**
     * Loads a True Type Font (.ttf) from a resource
     */
    fun loadTrueType(font: Resource, texSize: Int = 512, charHeight: Float = 8f, cdata: STBTTBakedChar.Buffer): TextureData {
        val ttf = font.inputStream.toByteBuffer().apply { flip() }
        val bitmap = BufferUtils.createByteBuffer(texSize * texSize)

        STBTruetype.stbtt_BakeFontBitmap(ttf, charHeight, bitmap, texSize, texSize, 32, cdata)
        return TextureData(bitmap, Vector2(texSize.toDouble()), GL_ALPHA, font, GL_ALPHA)
    }

    /**
     * Uploads the texture bitmap to OpenGL as a 1D texture
     */
    fun uploadTexture1D(data: TextureData): Texture {
        val id = glGenTextures()
        glEnable(TextureType.TEXTURE_1D.glID)
        glBindTexture(TextureType.TEXTURE_1D.glID, id)

        glTexImage1D(TextureType.TEXTURE_1D.glID, 0, data.format, data.size.xi, 0, data.outputFormat, GL_UNSIGNED_BYTE, data.bitMap)
        return Texture(id, data.size, data.resource, TextureType.TEXTURE_1D)
    }

    /**
     * Uploads the texture bitmap to OpenGL as a 2D texture
     */
    fun uploadTexture2D(data: TextureData): Texture {
        val id = glGenTextures()
        glEnable(TextureType.TEXTURE_2D.glID)
        glBindTexture(TextureType.TEXTURE_2D.glID, id)

        glTexImage2D(TextureType.TEXTURE_2D.glID, 0, data.format, data.size.xi, data.size.yi, 0, data.outputFormat, GL_UNSIGNED_BYTE, data.bitMap)

        return Texture(id, data.size, data.resource, TextureType.TEXTURE_2D)
    }

    /**
     * Uploads 6 textures to OpenGL to create a Texture Cube Map
     */
    fun uploadTextureCubeMap(datas: List<TextureData>): Texture {
        val id = glGenTextures()
        glEnable(TextureType.TEXTURE_CUBE.glID)
        glBindTexture(TextureType.TEXTURE_CUBE.glID, id)

        for (i in 0..5) {
            val data = datas[i]
            glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, data.format, data.size.xi, data.size.yi, 0, data.outputFormat, GL_UNSIGNED_BYTE, data.bitMap)
        }

        return Texture(id, datas[0].size, datas[0].resource, TextureType.TEXTURE_CUBE)
    }
}
package com.cout970.engine.renderer

import com.cout970.engine.tessellator.VaoBuilder
import org.lwjgl.BufferUtils

/**
 * Created by cout970 on 30/07/2016.
 */
/**
 * This vao format is made of 3 components:
 * Position (3 elements), Texture (2 elements), Normal (3 elements)
 */
class VaoFormatPTN : IVaoFormat {

    companion object {
        val MAX_VERTEX = 1024 * 512
        val INSTANCE = VaoFormatPTN()
    }

    var vertexCount = 0
    var textureCount = 0
    var normalCount = 0

    val vertex = BufferUtils.createFloatBuffer(MAX_VERTEX * 3)
    val texture = BufferUtils.createFloatBuffer(MAX_VERTEX * 2)
    val normal = BufferUtils.createFloatBuffer(MAX_VERTEX * 3)

    override fun set(slot: Int, vertexIndex: Int, vararg values: Number) {
        when (slot) {
            0 -> {
                vertex.put(vertexIndex * 3, values[0].toFloat())
                vertex.put(vertexIndex * 3 + 1, values[1].toFloat())
                vertex.put(vertexIndex * 3 + 2, values[2].toFloat())
                vertexCount = Math.max(vertexIndex * 3 + 3, vertexCount)
            }
            1 -> {
                texture.put(vertexIndex * 2, values[0].toFloat())
                texture.put(vertexIndex * 2 + 1, values[1].toFloat())
                textureCount = Math.max(vertexIndex * 2 + 2, textureCount)
            }
            2 -> {
                normal.put(vertexIndex * 3, values[0].toFloat())
                normal.put(vertexIndex * 3 + 1, values[1].toFloat())
                normal.put(vertexIndex * 3 + 2, values[2].toFloat())
                normalCount = Math.max(vertexIndex * 3 + 3, normalCount)
            }
            else -> throw IllegalArgumentException("Invalid slot: $slot")
        }
    }

    override fun store(builder: VaoBuilder) {
        builder.bindVertex(vertex = vertex.apply { limit(vertexCount) }, elementsPerVertex = 3)
        builder.bindTexture(uv = texture.apply { limit(textureCount) })
        builder.bindNormals(normals = normal.apply { limit(normalCount) })
    }

    override fun reset() {
        vertex.rewind()
        texture.rewind()
        normal.rewind()
        vertexCount = 0
        textureCount = 0
        normalCount = 0
    }
}
package com.cout970.engine.renderer

import com.cout970.engine.tessellator.VaoBuilder
import org.lwjgl.BufferUtils

/**
 * Created by cout970 on 11/08/2016.
 */
/**
 * This vao format is made of 2 components:
 * Position (2 elements), Color (3 elements)
 */
class VaoFormatPC : IVaoFormat {

    companion object {
        val MAX_VERTEX = 1024 * 512
        val INSTANCE = VaoFormatPC()
    }

    var vertexCount = 0
    var colorCount = 0

    val vertex = BufferUtils.createFloatBuffer(MAX_VERTEX * 2)!!
    val color = BufferUtils.createFloatBuffer(MAX_VERTEX * 3)!!

    override fun set(slot: Int, vertexIndex: Int, vararg values: Number) {
        when (slot) {
            0 -> {
                vertex.put(vertexIndex * 2, values[0].toFloat())
                vertex.put(vertexIndex * 2 + 1, values[1].toFloat())
                vertexCount = Math.max(vertexIndex * 2 + 2, vertexCount)
            }
            1 -> {
                color.put(vertexIndex * 3, values[0].toFloat())
                color.put(vertexIndex * 3 + 1, values[1].toFloat())
                color.put(vertexIndex * 3 + 2, values[2].toFloat())
                colorCount = Math.max(vertexIndex * 3 + 3, colorCount)
            }
            else -> throw IllegalArgumentException("Invalid slot: $slot")
        }
    }

    override fun store(builder: VaoBuilder) {
        vertex.limit(vertexCount)
        builder.vertexCount = vertex.remaining() / 2
        builder.bindAttrib(0, vertex, 2)
        builder.bindAttrib(1, color.apply { limit(colorCount) }, 3)
    }

    override fun reset() {
        vertex.rewind()
        color.rewind()
        vertexCount = 0
        colorCount = 0
    }
}
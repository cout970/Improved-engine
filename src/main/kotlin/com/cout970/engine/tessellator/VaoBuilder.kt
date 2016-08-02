package com.cout970.engine.tessellator

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.nio.FloatBuffer
import java.nio.IntBuffer

/**
 * Created by cout970 on 30/07/2016.
 */
class VaoBuilder(val drawMode: Int = GL11.GL_TRIANGLES, val longUse: Boolean = true) {

    var id: Int
    val buffers = mutableListOf<Int>()
    var useElements: Boolean = false
    var vertexCount = -1

    init {
        id = GL30.glGenVertexArrays()
        GL30.glBindVertexArray(id)
    }

    fun bindIndices(indices: IntBuffer): VaoBuilder {
        val vbo = GL15.glGenBuffers()

        vertexCount = indices.remaining()
        useElements = true

        buffers.add(vbo)

        GL30.glBindVertexArray(id)

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, if (longUse) GL15.GL_STATIC_DRAW else GL15.GL_STREAM_DRAW)

        GL30.glBindVertexArray(0)
        return this
    }

    fun bindVertex(slot: Int = 0, vertex: FloatBuffer, elementsPerVertex: Int): VaoBuilder {
        if (!useElements) {
            vertexCount = vertex.remaining() / elementsPerVertex
        }
        return bindAttrib(slot, vertex, elementsPerVertex)
    }

    fun bindTexture(slot: Int = 1, uv: FloatBuffer) = bindAttrib(slot, uv, 2)

    fun bindNormals(slot: Int = 2, normals: FloatBuffer) = bindAttrib(slot, normals, 3)

    fun bindAttrib(slot: Int, buffer: FloatBuffer, elementsPerVertex: Int): VaoBuilder {
        val vbo = GL15.glGenBuffers()
        buffers.add(vbo)

        GL30.glBindVertexArray(id)

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo)

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, if (longUse) GL15.GL_STATIC_DRAW else GL15.GL_STREAM_DRAW)
        GL20.glVertexAttribPointer(slot, elementsPerVertex, GL11.GL_FLOAT, false, 0, 0)

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)

        GL30.glBindVertexArray(0)
        return this
    }

    fun build(): VAO {
        if (vertexCount < 1) throw IllegalStateException("Invalid vertex count: $vertexCount")
        return VAO(id, vertexCount, buffers.size, buffers, drawMode, useElements)
    }
}
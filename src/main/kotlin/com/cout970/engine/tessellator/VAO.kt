package com.cout970.engine.tessellator

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.io.Closeable

/**
 * Created by cout970 on 30/07/2016.
 */
data class VAO(
        val id: Int,
        val vertexCount: Int,
        val vboCount: Int,
        val vbos: List<Int>,
        val drawMode: Int,
        val useElements: Boolean
) : Closeable {

    companion object {
        fun unbind(){
            GL30.glBindVertexArray(0)
        }
    }

    fun bind(){
        GL30.glBindVertexArray(id)
    }

    fun bindAttrib(){
        for(i in 0 until vbos.size) {
            GL20.glEnableVertexAttribArray(i)
        }
    }

    fun unbindAttrib(){
        for(i in 0 until vbos.size) {
            GL20.glDisableVertexAttribArray(i)
        }
    }

    fun draw(){
        if (useElements) {
            GL11.glDrawElements(drawMode, vertexCount, GL11.GL_UNSIGNED_INT, 0)
        } else {
            GL11.glDrawArrays(drawMode, 0, vertexCount)
        }
    }

    override fun close() {
        GL30.glDeleteVertexArrays(id)
        for (aVbo in vbos) {
            GL15.glDeleteBuffers(aVbo)
        }
    }
}
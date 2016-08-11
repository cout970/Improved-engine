package com.cout970.engine.tessellator

import com.cout970.engine.renderer.IRenderer
import com.cout970.engine.renderer.IVaoFormat
import com.cout970.engine.renderer.VaoFormatPTN
import org.lwjgl.opengl.GL11

/**
 * Created by cout970 on 30/07/2016.
 */

object Tessellator {

    var builder: VaoBuilder? = null
    private var vaoFormat: IVaoFormat = VaoFormatPTN.INSTANCE
    var vertexIndex = 0
    var vaoCache: VAO? = null

    fun setFormat(format: IVaoFormat) {
        vaoFormat = format
    }

    fun set(slot: Int, vararg values: Number): Tessellator {
        vaoFormat.set(slot, vertexIndex, *values)
        return this
    }

    fun endVertex() {
        vertexIndex++
    }

    fun startCompilation(drawMode: Int = GL11.GL_TRIANGLES) {
        builder = VaoBuilder(drawMode, true)
        vertexIndex = 0
        vaoFormat.reset()
    }

    fun endCompilation(): VAO {
        vaoFormat.store(builder!!)
        vaoCache = builder!!.build()
        return vaoCache!!
    }


    fun startDrawing(drawMode: Int = GL11.GL_TRIANGLES) {
        builder = VaoBuilder(drawMode, false)
        vertexIndex = 0
        vaoFormat.reset()
    }

    fun endDrawing() {
        vaoFormat.store(builder!!)
        vaoCache = builder!!.build()
    }

    fun drawCache(renderer: IRenderer){
        renderer.render(vaoCache!!)
        vaoCache!!.close()
        vaoCache = null
    }

    fun compile(drawMode: Int = GL11.GL_TRIANGLES, func: Tessellator.() -> Unit): VAO{
        startCompilation(drawMode)
        func()
        return endCompilation()
    }

    fun draw(drawMode: Int = GL11.GL_TRIANGLES, renderer: IRenderer, func: Tessellator.() -> Unit){
        startDrawing(drawMode)
        func()
        endDrawing()
        drawCache(renderer)
    }
}
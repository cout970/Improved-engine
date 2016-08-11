package com.cout970.engine.renderer

import com.cout970.engine.tessellator.VAO

/**
 * Created by cout970 on 30/07/2016.
 */
/**
 *
 */
interface IRenderer {

    fun preRender()

    fun render(vao: VAO)

    fun postRender()
}
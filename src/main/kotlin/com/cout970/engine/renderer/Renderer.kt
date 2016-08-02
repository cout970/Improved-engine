package com.cout970.engine.renderer

import com.cout970.engine.tessellator.IVaoRenderer
import com.cout970.engine.tessellator.VAO

/**
 * Created by cout970 on 30/07/2016.
 */
open class Renderer : IVaoRenderer {

    override fun render(vao: VAO) {
        vao.bind()
        vao.bindAttrib()
        vao.draw()
        vao.unbindAttrib()
        VAO.unbind()
    }
}
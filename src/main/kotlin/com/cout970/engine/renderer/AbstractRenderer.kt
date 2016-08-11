package com.cout970.engine.renderer

import com.cout970.engine.renderer.IRenderer
import com.cout970.engine.tessellator.VAO

/**
 * Created by cout970 on 30/07/2016.
 */
abstract class AbstractRenderer : IRenderer {

    override fun render(vao: VAO) {
        vao.bind()
        vao.bindAttrib()
        vao.draw()
        vao.unbindAttrib()
        VAO.unbind()
    }

}
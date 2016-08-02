package com.cout970.engine.window

import com.cout970.engine.util.box.Box2
import org.joml.Vector2d

/**
 * Created by cout970 on 25/07/2016.
 */
interface IWindow : AutoCloseable {

    val id: Long
    val box: Box2

    fun setPos(pos: Vector2d)

    fun setSize(size: Vector2d)

    fun shouldClose(): Boolean

    //show or hide the window
    fun show()

    fun hide()

    //minimize/iconify the window
    fun minimize()

    fun maximize()

    fun restore()

    //center window
    fun center()

    //focus the window
    fun requestFocus()

    fun hasFocus(): Boolean

    //vSync
    fun setVSync(active: Boolean)

    fun swapBuffers()

    //add a size change listener
    fun registerSizeChangeListener(listener: (Int, Int) -> Unit)

    fun registerWindowsFocusListener(listener: (Boolean) -> Unit)

    fun setViewport(box: Box2)
}
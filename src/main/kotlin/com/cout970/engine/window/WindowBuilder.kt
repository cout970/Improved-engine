package com.cout970.engine.window

import com.cout970.engine.util.math.xi
import com.cout970.engine.util.math.yi
import org.joml.Vector2d
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil

/**
 * Created by cout970 on 25/07/2016.
 */
class WindowBuilder {

    var title = "Window"
    var pos: Vector2d? = null
    var size: Vector2d? = null

    fun create(other: Long = MemoryUtil.NULL): IWindow {

        if (size == null) throw IllegalStateException("Size must not be null")

        val id = glfwCreateWindow(size!!.xi, size!!.yi, title, MemoryUtil.NULL, other)
        if (id == MemoryUtil.NULL) {
            throw RuntimeException("Failed to create a GLFW window")
        }

        glfwMakeContextCurrent(id)
        val window = WindowGLFW(id, title)

        window.setSize(size!!)
        if (pos != null) {
            window.setPos(pos!!)
        }
        return window
    }

    fun loadDefaultProperties() {
        glfwDefaultWindowHints()
    }

    fun setProperty(property: Int, value: Int) {
        glfwWindowHint(property, value)
    }
}
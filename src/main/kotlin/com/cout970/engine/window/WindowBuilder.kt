package com.cout970.engine.window

import com.cout970.engine.util.math.Vector2
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil

/**
 * Created by cout970 on 25/07/2016.
 */
class WindowBuilder {

    var title = "Window"
    var pos: Vector2? = null
    var size: Vector2? = null

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

    companion object {

        inline fun build(init: WindowBuilder.() -> Unit): IWindow {
            val builder = WindowBuilder()
            builder.init()
            return builder.create()
        }
    }
}
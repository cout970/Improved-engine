package com.cout970.engine.window

import com.cout970.engine.util.box.Box2
import com.cout970.engine.util.math.Vector2
import com.cout970.engine.util.math.vec2Of
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWFramebufferSizeCallback
import org.lwjgl.glfw.GLFWWindowFocusCallback
import org.lwjgl.opengl.GL11

/**
 * Created by cout970 on 25/07/2016.
 */
class WindowGLFW(
        override val id: Long,
        var title: String
) : IWindow {

    override var box: Box2 = Box2.IDENTITY
    var frameBufferCallBack: GLFWFramebufferSizeCallback
    var windowFocusCallback: GLFWWindowFocusCallback
    val sizeListeners = mutableListOf<(Int, Int) -> Unit>()
    val focusListeners = mutableListOf<(Boolean) -> Unit>()

    init {

        frameBufferCallBack = object : GLFWFramebufferSizeCallback() {
            override fun invoke(window: Long, width: Int, height: Int) {
                if (window == id) {
                    if (width != 0 && height != 0) box = box.copy(size = vec2Of(width, height))
                    sizeListeners.forEach { it.invoke(width, height) }
                }
            }
        }

        windowFocusCallback = object : GLFWWindowFocusCallback() {
            override fun invoke(window: Long, focused: Boolean) {
                if (window == id) {
                    focusListeners.forEach { it.invoke(focused) }
                }
            }
        }

        glfwSetFramebufferSizeCallback(id, frameBufferCallBack)
        glfwSetWindowFocusCallback(id, windowFocusCallback)
    }

    override fun setPos(pos: Vector2) {
        box = box.copy(pos = pos)
        glfwSetWindowPos(id, pos.xi, pos.yi)
    }

    override fun setSize(size: Vector2) {
        box = box.copy(size = size)
        glfwSetWindowSize(id, size.xi, size.yi)
    }

    override fun shouldClose(): Boolean = glfwWindowShouldClose(id)

    override fun show() {
        glfwShowWindow(id)
    }

    override fun hide() {
        glfwHideWindow(id)
    }

    override fun minimize() {
        glfwIconifyWindow(id)
    }

    override fun maximize() {
        glfwMaximizeWindow(id)
    }

    override fun restore() {
        glfwRestoreWindow(id)
    }

    override fun center() {
        val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())

        glfwSetWindowPos(id,
                (vidmode.width() - box.size.xi) / 2,
                (vidmode.height() - box.size.yi) / 2)
    }

    override fun requestFocus() {
        glfwFocusWindow(id)
    }

    override fun hasFocus(): Boolean = glfwGetWindowAttrib(id, GLFW_FOCUSED) == GLFW_TRUE

    override fun setVSync(active: Boolean) {
        glfwMakeContextCurrent(id)
        glfwSwapInterval(if (active) GLFW_TRUE else GLFW_FALSE)
    }

    override fun swapBuffers() {
        glfwSwapBuffers(id)
    }

    override fun close() {
        frameBufferCallBack.close()
        windowFocusCallback.close()
    }

    override fun registerSizeChangeListener(listener: (Int, Int) -> Unit) {
        sizeListeners.add(listener)
    }

    override fun registerWindowsFocusListener(listener: (Boolean) -> Unit) {
        focusListeners.add(listener)
    }

    override fun setViewport(box: Box2) {
        GL11.glViewport(box.pos.xi, box.pos.yi, box.size.xi, box.size.yi)
    }
}
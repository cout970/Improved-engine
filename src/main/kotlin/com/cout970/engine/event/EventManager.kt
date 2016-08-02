package com.cout970.engine.event

import com.cout970.engine.util.math.vec2Of
import com.cout970.engine.window.IWindow
import org.joml.Vector2d
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*

/**
 * Created by cout970 on 26/07/2016.
 */
object EventManager {

    private val listeners = mutableListOf<(Event) -> Unit>()
    private val callbacks = mutableListOf<Callback>()

    fun isKeyPressed(window: IWindow, key: Int): Boolean {
        return glfwGetKey(window.id, key) == GLFW_PRESS
    }

    fun registerWindow(windowID: Long) {
        callbacks.add(Callback(windowID))
    }

    fun fireEvent(event: Event) {
        listeners.forEach { it.invoke(event) }
    }

    fun registerListener(listener: (Event) -> Unit) {
        listeners.add(listener)
    }

    fun pollEvents() {
        glfwPollEvents()
    }

    private class Callback(windowID: Long) : AutoCloseable {

        val charCallback: GLFWCharCallback
        val keyCallback: GLFWKeyCallback
        val mouseButtonCallback: GLFWMouseButtonCallback
        val scrollCallback: GLFWScrollCallback
        val cursorPosCallback: GLFWCursorPosCallback

        init {
            charCallback = GLFWCharCallback.create { window, code -> fireEvent(EventCharTyped(window, code.toChar(), code)) }
            keyCallback = GLFWKeyCallback.create { window, key, scancode, action, mods -> fireEvent(EventKeyUpdate(window, key, scancode, EnumAction.fromID(action), mods)) }
            mouseButtonCallback = GLFWMouseButtonCallback.create { window, button, action, mods -> fireEvent(EventMouseClick(window, button, EnumAction.fromID(action), mods)) }
            scrollCallback = GLFWScrollCallback.create { window, xoffset, yoffset -> fireEvent(EventMouseScroll(window, xoffset, yoffset)) }
            cursorPosCallback = GLFWCursorPosCallback.create { window, x, y -> fireEvent(EventCursorPos(window, x, y)) }

            glfwSetCharCallback(windowID, charCallback)
            glfwSetKeyCallback(windowID, keyCallback)
            glfwSetMouseButtonCallback(windowID, mouseButtonCallback)
            glfwSetScrollCallback(windowID, scrollCallback)
            glfwSetCursorPosCallback(windowID, cursorPosCallback)
        }

        override fun close() {
            charCallback.close()
            keyCallback.close()
            mouseButtonCallback.close()
            scrollCallback.close()
            cursorPosCallback.close()
        }
    }

    fun getMousePos(window: IWindow): Vector2d {
        val x = DoubleArray(1)
        val y = DoubleArray(1)
        glfwGetCursorPos(window.id, x, y)
        return vec2Of(x[0], window.box.size.y -y[0])
    }
}
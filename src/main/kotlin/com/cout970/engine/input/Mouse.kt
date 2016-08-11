package com.cout970.engine.input

import com.cout970.engine.util.math.Vector2
import com.cout970.engine.util.math.vec2Of
import com.cout970.engine.window.IWindow
import org.lwjgl.glfw.GLFW

/**
 * Created by cout970 on 08/08/2016.
 */
/**
 * A utility class to get mouse info
 */
open class Mouse {

    companion object {
        val BUTTON_LEFT = GLFW.GLFW_MOUSE_BUTTON_LEFT
        val BUTTON_MIDDLE = GLFW.GLFW_MOUSE_BUTTON_MIDDLE
        val BUTTON_RIGHT = GLFW.GLFW_MOUSE_BUTTON_RIGHT
    }

    private var lastMousePos = Vector2.ORIGIN
    private var mousePos = Vector2.ORIGIN
    //The window used to check the state of keys and position
    val window: IWindow

    constructor(window: IWindow) {
        this.window = window
    }

    /**
     * This function should be called every frame to update the position of the mouse
     */
    fun update() {
        val x = DoubleArray(1)
        val y = DoubleArray(1)
        GLFW.glfwGetCursorPos(window.id, x, y)
        mousePos = vec2Of(x[0], y[0])
    }

    /**
     * Gets the difference of the last mouse position and the current mouse position
     */
    fun getMousePosDiff() = getMousePos() - lastMousePos

    /**
     * Gets the current position of the mouse
     */
    fun getMousePos() = mousePos

    /**
     * Checks if the mouse button is pressed or not
     */
    fun isButtonPressed(button: Int): Boolean = GLFW.glfwGetMouseButton(window.id, button) == GLFW.GLFW_PRESS
}
package com.cout970.engine.event

import org.lwjgl.glfw.GLFW

/**
 * Created by cout970 on 26/07/2016.
 */

val MOUSE_BUTTON_LEFT = GLFW.GLFW_MOUSE_BUTTON_LEFT
val MOUSE_BUTTON_MIDDLE = GLFW.GLFW_MOUSE_BUTTON_MIDDLE
val MOUSE_BUTTON_RIGHT = GLFW.GLFW_MOUSE_BUTTON_RIGHT

class EventMouseClick(
        id: Long,
        val button: Int,
        val action: EnumAction,
        val mods: Int
) : Event(id) {

    override fun toString(): String{
        return "EventMouseClick(button=$button, action=$action, mods=$mods)"
    }
}

package com.cout970.engine.event

import org.lwjgl.glfw.GLFW

/**
 * Created by cout970 on 26/07/2016.
 */
/**
 * This event is fired when the user clicks inside the window
 */
class EventMouseClick(
        id: Long,
        val button: Int,
        val keyState: EnumKeyState,
        val mods: Int
) : Event(id) {

    override fun toString(): String{
        return "EventMouseClick(button=$button, action=$keyState, mods=$mods)"
    }

    companion object {
        val MOD_SHIFT = GLFW .GLFW_MOD_SHIFT
        val MOD_CONTROL = GLFW.GLFW_MOD_CONTROL
        val MOD_ALT = GLFW.GLFW_MOD_ALT
        val MOD_SUPER = GLFW.GLFW_MOD_SUPER
    }
}

package com.cout970.engine.event

/**
 * Created by cout970 on 26/07/2016.
 */
class EventKeyUpdate(
        id: Long,
        val keycode: Int,
        val scancode: Int,
        val action: EnumAction,
        val mods: Int
) : Event(id) {

    override fun toString(): String{
        return "EventKeyUpdate(keycode=$keycode, scancode=$scancode, action=$action, mods=$mods)"
    }
}
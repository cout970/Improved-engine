package com.cout970.engine.event

/**
 * Created by cout970 on 26/07/2016.
 */
/**
 * This event is fired when the state of some key changes
 */
class EventKeyUpdate(
        id: Long,
        val keycode: Int,
        val scanCode: Int,
        val keyState: EnumKeyState,
        val mods: Int
) : Event(id) {

    override fun toString(): String{
        return "EventKeyUpdate(keycode=$keycode, scanCode=$scanCode, action=$keyState, mods=$mods)"
    }
}
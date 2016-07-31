package com.cout970.engine.event

/**
 * Created by cout970 on 26/07/2016.
 */
class EventCursorPos(
        id: Long,
        val x: Double,
        val y: Double
) : Event(id) {

    override fun toString(): String{
        return "EventCursorPos(x=$x, y=$y)"
    }
}
package com.cout970.engine.event

/**
 * Created by cout970 on 26/07/2016.
 */
class EventMouseScroll(
        id: Long,
        val offsetX: Double,
        val offsetY: Double
) : Event(id) {

    override fun toString(): String{
        return "EventMouseScroll(offsetX=$offsetX, offsetY=$offsetY)"
    }
}
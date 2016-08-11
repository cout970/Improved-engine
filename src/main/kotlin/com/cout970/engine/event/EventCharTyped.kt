package com.cout970.engine.event

/**
 * Created by cout970 on 26/07/2016.
 */
/**
 * This event is fired when some key is typed to be used in text input systems
 */
class EventCharTyped(
        id: Long,
        val char: Char,
        val code: Int
) : Event(id){

    override fun toString(): String{
        return "EventCharTyped(char=$char, code=$code)"
    }
}
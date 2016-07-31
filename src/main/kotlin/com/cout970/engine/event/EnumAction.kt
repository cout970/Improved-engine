package com.cout970.engine.event

/**
 * Created by cout970 on 26/07/2016.
 */
enum class EnumAction {
    RELEASE,
    PRESS,
    REPEAT;

    companion object {
        fun fromID(id: Int) = when (id) {
            0 -> RELEASE
            1 -> PRESS
            2 -> REPEAT
            else -> throw IllegalArgumentException("Invalid id: $id")
        }
    }
}
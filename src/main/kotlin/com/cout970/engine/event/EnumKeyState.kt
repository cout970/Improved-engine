package com.cout970.engine.event

/**
 * Created by cout970 on 26/07/2016.
 */
/**
 * This class represents the state of a key
 */
enum class EnumKeyState {
    RELEASE,
    PRESS,
    REPEAT;

    companion object {

        /**
         * Converts a GLFW key state code to a EnumKeyState
         */
        fun fromID(id: Int) = when (id) {
            0 -> RELEASE
            1 -> PRESS
            2 -> REPEAT
            else -> throw IllegalArgumentException("Invalid id: $id")
        }
    }
}
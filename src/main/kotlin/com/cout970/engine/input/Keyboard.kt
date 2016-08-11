package com.cout970.engine.input

import com.cout970.engine.event.EnumKeyState
import com.cout970.engine.window.IWindow
import org.lwjgl.glfw.GLFW

/**
 * Created by cout970 on 09/08/2016.
 */
/**
 * A utility class to get keyboard info
 */
object Keyboard {

    //The window used to check key states
    private val windows = mutableSetOf<Long>()

    /**
     * Register a window to retrieve key states
     */
    fun registerWindow(window: IWindow) {
        windows.add(window.id)
    }

    /**
     * Gets the name of a key
     */
    fun getKeyName(key: Int, scanCode: Int) = GLFW.glfwGetKeyName(key, scanCode)

    /**
     * Checks if a key is pressed
     */
    fun isKeyPressed(key: Int) = windows.any { GLFW.glfwGetKey(it, key) == GLFW.GLFW_PRESS }

    /**
     * Gets the current state of a key
     */
    fun getKeyState(key: Int) = windows.map { EnumKeyState.fromID(GLFW.glfwGetKey(it, key)) }
            .reduce { state, keyState -> if(state == EnumKeyState.RELEASE) keyState else state }

    //##########################
    //        CONSTANTS
    //##########################

    //Unknown key
    val KEY_UNKNOWN = GLFW.GLFW_KEY_UNKNOWN

    //Keyboard keys
    val KEY_SPACE = GLFW.GLFW_KEY_SPACE
    val KEY_APOSTROPHE = GLFW.GLFW_KEY_APOSTROPHE
    val KEY_COMMA = GLFW.GLFW_KEY_COMMA
    val KEY_MINUS = GLFW.GLFW_KEY_MINUS
    val KEY_PERIOD = GLFW.GLFW_KEY_PERIOD
    val KEY_SLASH = GLFW.GLFW_KEY_SLASH
    val KEY_0 = GLFW.GLFW_KEY_0
    val KEY_1 = GLFW.GLFW_KEY_1
    val KEY_2 = GLFW.GLFW_KEY_2
    val KEY_3 = GLFW.GLFW_KEY_3
    val KEY_4 = GLFW.GLFW_KEY_4
    val KEY_5 = GLFW.GLFW_KEY_5
    val KEY_6 = GLFW.GLFW_KEY_6
    val KEY_7 = GLFW.GLFW_KEY_7
    val KEY_8 = GLFW.GLFW_KEY_8
    val KEY_9 = GLFW.GLFW_KEY_9
    val KEY_SEMICOLON = GLFW.GLFW_KEY_SEMICOLON
    val KEY_EQUAL = GLFW.GLFW_KEY_EQUAL
    val KEY_A = GLFW.GLFW_KEY_A
    val KEY_B = GLFW.GLFW_KEY_B
    val KEY_C = GLFW.GLFW_KEY_C
    val KEY_D = GLFW.GLFW_KEY_D
    val KEY_E = GLFW.GLFW_KEY_E
    val KEY_F = GLFW.GLFW_KEY_F
    val KEY_G = GLFW.GLFW_KEY_G
    val KEY_H = GLFW.GLFW_KEY_H
    val KEY_I = GLFW.GLFW_KEY_I
    val KEY_J = GLFW.GLFW_KEY_J
    val KEY_K = GLFW.GLFW_KEY_K
    val KEY_L = GLFW.GLFW_KEY_L
    val KEY_M = GLFW.GLFW_KEY_M
    val KEY_N = GLFW.GLFW_KEY_N
    val KEY_O = GLFW.GLFW_KEY_O
    val KEY_P = GLFW.GLFW_KEY_P
    val KEY_Q = GLFW.GLFW_KEY_Q
    val KEY_R = GLFW.GLFW_KEY_R
    val KEY_S = GLFW.GLFW_KEY_S
    val KEY_T = GLFW.GLFW_KEY_T
    val KEY_U = GLFW.GLFW_KEY_U
    val KEY_V = GLFW.GLFW_KEY_V
    val KEY_W = GLFW.GLFW_KEY_W
    val KEY_X = GLFW.GLFW_KEY_X
    val KEY_Y = GLFW.GLFW_KEY_Y
    val KEY_Z = GLFW.GLFW_KEY_Z
    val KEY_LEFT_BRACKET = GLFW.GLFW_KEY_LEFT_BRACKET
    val KEY_BACKSLASH = GLFW.GLFW_KEY_BACKSLASH
    val KEY_RIGHT_BRACKET = GLFW.GLFW_KEY_RIGHT_BRACKET
    val KEY_GRAVE_ACCENT = GLFW.GLFW_KEY_GRAVE_ACCENT
    val KEY_WORLD_1 = GLFW.GLFW_KEY_WORLD_1
    val KEY_WORLD_2 = GLFW.GLFW_KEY_WORLD_2
    val KEY_ESCAPE = GLFW.GLFW_KEY_ESCAPE
    val KEY_ENTER = GLFW.GLFW_KEY_ENTER
    val KEY_TAB = GLFW.GLFW_KEY_TAB
    val KEY_BACKSPACE = GLFW.GLFW_KEY_BACKSPACE
    val KEY_INSERT = GLFW.GLFW_KEY_INSERT
    val KEY_DELETE = GLFW.GLFW_KEY_DELETE
    val KEY_RIGHT = GLFW.GLFW_KEY_RIGHT
    val KEY_LEFT = GLFW.GLFW_KEY_LEFT
    val KEY_DOWN = GLFW.GLFW_KEY_DOWN
    val KEY_UP = GLFW.GLFW_KEY_UP
    val KEY_PAGE_UP = GLFW.GLFW_KEY_PAGE_UP
    val KEY_PAGE_DOWN = GLFW.GLFW_KEY_PAGE_DOWN
    val KEY_HOME = GLFW.GLFW_KEY_HOME
    val KEY_END = GLFW.GLFW_KEY_END
    val KEY_CAPS_LOCK = GLFW.GLFW_KEY_CAPS_LOCK
    val KEY_SCROLL_LOCK = GLFW.GLFW_KEY_SCROLL_LOCK
    val KEY_NUM_LOCK = GLFW.GLFW_KEY_NUM_LOCK
    val KEY_PRINT_SCREEN = GLFW.GLFW_KEY_PRINT_SCREEN
    val KEY_PAUSE = GLFW.GLFW_KEY_PAUSE
    val KEY_F1 = GLFW.GLFW_KEY_F1
    val KEY_F2 = GLFW.GLFW_KEY_F2
    val KEY_F3 = GLFW.GLFW_KEY_F3
    val KEY_F4 = GLFW.GLFW_KEY_F4
    val KEY_F5 = GLFW.GLFW_KEY_F5
    val KEY_F6 = GLFW.GLFW_KEY_F6
    val KEY_F7 = GLFW.GLFW_KEY_F7
    val KEY_F8 = GLFW.GLFW_KEY_F8
    val KEY_F9 = GLFW.GLFW_KEY_F9
    val KEY_F10 = GLFW.GLFW_KEY_F10
    val KEY_F11 = GLFW.GLFW_KEY_F11
    val KEY_F12 = GLFW.GLFW_KEY_F12
    val KEY_F13 = GLFW.GLFW_KEY_F13
    val KEY_F14 = GLFW.GLFW_KEY_F14
    val KEY_F15 = GLFW.GLFW_KEY_F15
    val KEY_F16 = GLFW.GLFW_KEY_F16
    val KEY_F17 = GLFW.GLFW_KEY_F17
    val KEY_F18 = GLFW.GLFW_KEY_F18
    val KEY_F19 = GLFW.GLFW_KEY_F19
    val KEY_F20 = GLFW.GLFW_KEY_F20
    val KEY_F21 = GLFW.GLFW_KEY_F21
    val KEY_F22 = GLFW.GLFW_KEY_F22
    val KEY_F23 = GLFW.GLFW_KEY_F23
    val KEY_F24 = GLFW.GLFW_KEY_F24
    val KEY_F25 = GLFW.GLFW_KEY_F25
    val KEY_KP_0 = GLFW.GLFW_KEY_KP_0
    val KEY_KP_1 = GLFW.GLFW_KEY_KP_1
    val KEY_KP_2 = GLFW.GLFW_KEY_KP_2
    val KEY_KP_3 = GLFW.GLFW_KEY_KP_3
    val KEY_KP_4 = GLFW.GLFW_KEY_KP_4
    val KEY_KP_5 = GLFW.GLFW_KEY_KP_5
    val KEY_KP_6 = GLFW.GLFW_KEY_KP_6
    val KEY_KP_7 = GLFW.GLFW_KEY_KP_7
    val KEY_KP_8 = GLFW.GLFW_KEY_KP_8
    val KEY_KP_9 = GLFW.GLFW_KEY_KP_9
    val KEY_KP_DECIMAL = GLFW.GLFW_KEY_KP_DECIMAL
    val KEY_KP_DIVIDE = GLFW.GLFW_KEY_KP_DIVIDE
    val KEY_KP_MULTIPLY = GLFW.GLFW_KEY_KP_MULTIPLY
    val KEY_KP_SUBTRACT = GLFW.GLFW_KEY_KP_SUBTRACT
    val KEY_KP_ADD = GLFW.GLFW_KEY_KP_ADD
    val KEY_KP_ENTER = GLFW.GLFW_KEY_KP_ENTER
    val KEY_KP_EQUAL = GLFW.GLFW_KEY_KP_EQUAL
    val KEY_LEFT_SHIFT = GLFW.GLFW_KEY_LEFT_SHIFT
    val KEY_LEFT_CONTROL = GLFW.GLFW_KEY_LEFT_CONTROL
    val KEY_LEFT_ALT = GLFW.GLFW_KEY_LEFT_ALT
    val KEY_LEFT_SUPER = GLFW.GLFW_KEY_LEFT_SUPER
    val KEY_RIGHT_SHIFT = GLFW.GLFW_KEY_RIGHT_SHIFT
    val KEY_RIGHT_CONTROL = GLFW.GLFW_KEY_RIGHT_CONTROL
    val KEY_RIGHT_ALT = GLFW.GLFW_KEY_RIGHT_ALT
    val KEY_RIGHT_SUPER = GLFW.GLFW_KEY_RIGHT_SUPER
    val KEY_MENU = GLFW.GLFW_KEY_MENU
    val KEY_LAST = GLFW.GLFW_KEY_LAST
}
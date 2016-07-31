package com.cout970.engine.util

import org.lwjgl.glfw.GLFW

import org.lwjgl.glfw.GLFW.glfwGetTime

class Timer {

    private var lastSecond: Double = 0.toDouble()
    private var fpsCount: Int = 0
    var fps: Int = 0
        private set
    var delta: Double = 0.toDouble()
        private set
    private var time: Double = 0.toDouble()

    init {
        lastSecond = GLFW.glfwGetTime()
    }

    fun loopTick() {
        delta = glfwGetTime() - time
        time = glfwGetTime()

        fpsCount++
        if (time - lastSecond >= 1) {
            fps = fpsCount
            fpsCount = 0
            lastSecond = time
        }
    }

    val secTime: Double
        get() = glfwGetTime()

    val miliTime: Double
        get() = glfwGetTime() / 1000

    val nanoTime: Double
        get() = glfwGetTime() / (1000 * 1000 * 1000)
}

package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.util.GameLoop
import com.cout970.engine.util.math.vec2Of
import com.cout970.engine.window.IWindow
import com.cout970.engine.window.WindowBuilder

/**
 * Created by cout970 on 11/08/2016.
 */

fun main(args: Array<String>) {
    Loader.init()
    TestMultiWindow.create()
    Loader.initOpenGL()
    TestMultiWindow.run()
    Loader.close()
}

object TestMultiWindow {

    lateinit var window1: IWindow
    lateinit var window2: IWindow
    lateinit var gameLoop: GameLoop

    fun create() {
        window1 = WindowBuilder.build {
            loadDefaultProperties()
            title = "TestWindow 1"
            size = vec2Of(300, 500)
            pos = vec2Of(100, 50)
        }
        window2 = WindowBuilder.build {
            loadDefaultProperties()
            title = "TestWindow 2"
            size = vec2Of(300, 500)
            pos = vec2Of(500, 50)
        }
    }

    fun run() {
        gameLoop = GameLoop {

            window1.swapBuffers()
            window2.swapBuffers()
            EventManager.pollEvents()
            if (window1.shouldClose()) gameLoop.stop()
            if (window2.shouldClose()) gameLoop.stop()
        }
        gameLoop.start()
    }
}
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
    TestWindow.create()
    Loader.initOpenGL()
    TestWindow.run()
    Loader.close()
}

object TestWindow {

    lateinit var window: IWindow
    lateinit var gameLoop: GameLoop

    fun create() {
        window = WindowBuilder.build {
            loadDefaultProperties()
            title = "TestWindow"
            size = vec2Of(300, 500)
            pos = vec2Of(100, 50)
        }
    }

    fun run() {
        gameLoop = GameLoop {

            window.swapBuffers()
            EventManager.pollEvents()
            if (window.shouldClose()) gameLoop.stop()
        }
        gameLoop.start()
    }
}
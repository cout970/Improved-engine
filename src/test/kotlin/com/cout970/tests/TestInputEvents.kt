package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.input.Keyboard
import com.cout970.engine.util.GameLoop
import com.cout970.engine.util.math.vec2Of
import com.cout970.engine.window.IWindow
import com.cout970.engine.window.WindowBuilder

/**
 * Created by cout970 on 11/08/2016.
 */

fun main(args: Array<String>) {
    Loader.init()
    TestInputEvents.create()
    Loader.initOpenGL()
    TestInputEvents.run()
    Loader.close()
}

object TestInputEvents {

    lateinit var window: IWindow
    lateinit var window2: IWindow
    lateinit var gameLoop: GameLoop

    fun create() {
        window = WindowBuilder.build {
            loadDefaultProperties()
            title = "TestWindow 1"
            size = vec2Of(300, 500)
            pos = vec2Of(100, 50)
        }
        window2 = WindowBuilder.build {
            loadDefaultProperties()
            title = "TestWindow 2"
            size = vec2Of(300, 500)
            pos = vec2Of(100, 50)
        }
    }

    fun run() {

        EventManager.registerWindow(window.id)
        EventManager.registerWindow(window2.id)
        Keyboard.registerWindow(window)
        Keyboard.registerWindow(window2)

        EventManager.registerListener{
            println("${it.windowID}: $it")
        }

        gameLoop = GameLoop {

            println(Keyboard.getKeyState(Keyboard.KEY_0))
            window.swapBuffers()
            window2.swapBuffers()
            EventManager.pollEvents()
            if (window.shouldClose() or window2.shouldClose()) gameLoop.stop()
        }
        gameLoop.start()
    }
}
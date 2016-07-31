package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.window.WindowBuilder
import org.joml.Vector2d

/**
 * Created by cout970 on 26/07/2016.
 */

fun main(args: Array<String>) {
    val builder = WindowBuilder()
    builder.pos = Vector2d(20.0, 30.0)
    builder.size = Vector2d(500.0, 500.0)
    builder.title = "Tile 1"

    builder.loadDefaultProperties()

    Loader.init()

    val window = builder.create()
    builder.title = "Tile 2"
    val window2 = builder.create(window.id)

    EventManager.registerWindow(window.id)
    EventManager.registerWindow(window2.id)

    EventManager.registerListener { println(it) }

    Loader.initOpenGL()

    while (true) {

        //actualizacion de frame
        window.swapBuffers()
        window2.swapBuffers()
        //gestion de eventos como el teclado
        EventManager.pollEvents()
        if (window.shouldClose()) break
        if (window2.shouldClose()) break
    }

    Loader.close()
}
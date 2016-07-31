package com.cout970.tests

import com.cout970.engine.Loader
import com.cout970.engine.event.EventManager
import com.cout970.engine.window.WindowBuilder
import org.joml.Vector2d
import org.lwjgl.glfw.GLFW

/**
 * Created by cout970 on 25/07/2016.
 */

fun main(args: Array<String>) {
    Loader.init()

    val builder = WindowBuilder()
    builder.pos = Vector2d(20.0, 30.0)
    builder.size = Vector2d(500.0, 500.0)
    builder.title = "Tile 1"

    builder.loadDefaultProperties()

    val window = builder.create()
    builder.title = "Tile 2"
    val window2 = builder.create()

    Loader.initOpenGL()

    val mouseX = DoubleArray(1)
    val mouseY = DoubleArray(1)

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
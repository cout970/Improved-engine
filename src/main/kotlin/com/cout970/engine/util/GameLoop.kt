package com.cout970.engine.util

/**
 * Created by cout970 on 11/08/2016.
 */
open class GameLoop(val tick: () -> Unit) {

    var running = true
        private set

    fun start(){
        while(running){
            tick()
        }
        running = true
    }

    fun stop(){
        running = false
    }
}
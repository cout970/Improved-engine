package com.cout970.tests

import com.cout970.engine.Loader

/**
 * Created by cout970 on 11/08/2016.
 */

fun main(args: Array<String>) {
    Loader.init()
    println("Hello World")
    Loader.close()
}
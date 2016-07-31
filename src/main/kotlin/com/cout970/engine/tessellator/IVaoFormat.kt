package com.cout970.engine.tessellator

/**
 * Created by cout970 on 30/07/2016.
 */
interface IVaoFormat {

    fun set(slot: Int, vertexIndex: Int, vararg values: Number)

    fun store(builder: VaoBuilder)

    fun reset()
}
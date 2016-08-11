package com.cout970.engine.renderer

import com.cout970.engine.tessellator.VaoBuilder

/**
 * Created by cout970 on 30/07/2016.
 */
/**
 * This interface abstracts the format used to store data in a VAO
 */
interface IVaoFormat {

    /**
     * Sets one value into the internal cache
     * @see VaoFormatPTN
     */
    fun set(slot: Int, vertexIndex: Int, vararg values: Number)

    /**
     * Stores all the data in the cache into the builder to create the VAO
     */
    fun store(builder: VaoBuilder)

    /**
     * Cleans the cache in this object
     */
    fun reset()
}
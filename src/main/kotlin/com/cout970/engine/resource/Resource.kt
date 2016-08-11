package com.cout970.engine.resource

import java.io.File
import java.io.InputStream

/**
 * Created by cout970 on 29/07/2016.
 */
/**
 * This class provides an abstraction to a resource needed by the program,
 * the resource domain is the
 */
data class Resource(
        val domain: ResourceDomain,
        val path: String
) {

    /**
     * Checks if this resource exists or not
     */
    val exists: Boolean get() = domain.exists(this)

    /**
     * Gets an input stream for the file/jar entry
     */
    val inputStream: InputStream get() = domain.getInputStream(this)

    /**
     * Gets a file for this resource, the root of this file will be the run folder
     */
    val file: File = domain.getFile(this)
}
package com.cout970.engine.shader

import com.cout970.engine.resource.Resource
import java.io.FileNotFoundException
import java.io.InputStream

/**
 * Created by cout970 on 31/07/2016.
 */
/**
 * This object allow the user to have
 *  #include "file"
 * in a shader program
 */
object ShaderPreprocessor {

    val INCLUDE_PREFIX = "#include \""
    val INCLUDE_SUFFIX = "\""

    /**
     * Loads a shader from a resource
     */
    fun loadShader(resource: Resource): String {
        return getLines(resource).joinToString("\n")
    }

    /**
     * Get all the lines of the shader
     */
    fun getLines(resource: Resource): List<String> {
        val lines = mutableListOf<String>()
        val stream: InputStream

        try {
            stream = resource.inputStream
        } catch (e: FileNotFoundException) {
            throw e
        }

        stream.reader().forEachLine {
            if (it.startsWith(INCLUDE_PREFIX) && it.endsWith(INCLUDE_SUFFIX)) {
                val name = it.substring(INCLUDE_PREFIX.length, it.length - INCLUDE_SUFFIX.length)
                val code = tryLoadFile(resource, name)
                lines.addAll(code)
            } else {
                lines.add(it)
            }
        }
        return lines
    }

    private fun tryLoadFile(resource: Resource, name: String): List<String> {
        return getLines(Resource(resource.domain, resource.path.trimEnd { it != '/' } + name))
    }
}
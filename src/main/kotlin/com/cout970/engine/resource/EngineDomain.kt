package com.cout970.engine.resource

import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

/**
 * Created by cout970 on 30/07/2016.
 */
object EngineDomain : ResourceDomain() {

    override val name: String = "Engine"

    override fun exists(res: Resource): Boolean {
        return ClassLoader.getSystemClassLoader().getResourceAsStream("assets/engine/${res.path}") == null
    }

    override fun getInputStream(res: Resource): InputStream {
        return ClassLoader.getSystemClassLoader().getResourceAsStream("assets/engine/${res.path}")
                ?: throw FileNotFoundException("resource: $res")
    }

    override fun getFile(res: Resource): File {
        return File("./${res.path}")
    }
}
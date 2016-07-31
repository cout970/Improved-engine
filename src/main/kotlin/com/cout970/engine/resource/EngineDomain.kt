package com.cout970.engine.resource

import java.io.FileNotFoundException
import java.io.InputStream

/**
 * Created by cout970 on 30/07/2016.
 */
object EngineDomain : ResourceDomain() {

    override val name: String = "Engine"

    override fun read(res: Resource): InputStream {
        return Thread.currentThread().contextClassLoader.
                getResourceAsStream("assets/${res.domain.name.toLowerCase()}/${res.path}")
                ?: throw FileNotFoundException("resource: $res")
    }
}
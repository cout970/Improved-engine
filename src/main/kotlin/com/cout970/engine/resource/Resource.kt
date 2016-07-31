package com.cout970.engine.resource

import java.io.InputStream

/**
 * Created by cout970 on 29/07/2016.
 */
data class Resource(
        val domain: ResourceDomain,
        val path: String
) {
    val inputStream: InputStream get() = domain.read(this)
}
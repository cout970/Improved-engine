package com.cout970.engine.resource

import java.io.File
import java.io.InputStream

/**
 * Created by cout970 on 30/07/2016.
 */
/**
 * This class represents a resource loader the abstracts the resources of the program
 */
abstract class ResourceDomain {

    abstract val name: String

    abstract fun exists(res: Resource): Boolean

    abstract fun getInputStream(res: Resource): InputStream

    abstract fun getFile(res: Resource): File

    override fun toString(): String{
        return "ResourceDomain(name='$name')"
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is ResourceDomain) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int{
        return name.hashCode()
    }


}
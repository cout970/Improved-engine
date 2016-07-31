package com.cout970.tests

import com.cout970.engine.resource.EngineDomain
import com.cout970.engine.resource.Resource
import com.cout970.engine.shader.ShaderPreprocesor

/**
 * Created by cout970 on 31/07/2016.
 */

fun main(args: Array<String>) {
    ShaderPreprocesor.loadShader(Resource(EngineDomain, "./shader_test.glsl"))
}

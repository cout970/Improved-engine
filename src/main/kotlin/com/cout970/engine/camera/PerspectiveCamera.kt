package com.cout970.engine.camera

import org.joml.Matrix4d

/**
 * Created by cout970 on 30/07/2016.
 */
class PerspectiveCamera(
        var fov: Double,
        var aspect: Double,
        var nearPlane: Double,
        var farPlane: Double
) : Camera(){

    override fun getProjection() = Matrix4d().apply { setPerspective(fov, aspect, nearPlane, farPlane) }
}
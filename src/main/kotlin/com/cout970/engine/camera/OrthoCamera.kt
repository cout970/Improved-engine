package com.cout970.engine.camera

import org.joml.Matrix4d

/**
 * Created by cout970 on 30/07/2016.
 */
/**
 * Implementation of Camera using an Orthographic projection
 */
open class OrthoCamera(
        var left: Double = -0.5,
        var right: Double = 0.5,
        var bottom: Double = -0.5,
        var top: Double = 0.5,
        var zNear: Double,
        var zFar: Double
) : Camera() {

    override fun getProjection() = Matrix4d().apply { setOrtho(left, right, bottom, top, zNear, zFar) }
}
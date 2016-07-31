package com.cout970.engine.camera

import com.cout970.engine.util.box.Box2
import org.joml.Matrix4d

/**
 * Created by cout970 on 30/07/2016.
 */
class OrthoCamera(
        var box: Box2,
        var zNear: Double,
        var zFar: Double
) : Camera() {

    override fun getProjection() = Matrix4d().apply { setOrtho(-0.5, 0.5, -0.5, 0.5, zNear, zFar) }
}
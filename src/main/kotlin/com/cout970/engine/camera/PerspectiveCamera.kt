package com.cout970.engine.camera

import com.cout970.engine.util.box.Box2
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

    override fun updateViewport(viewport: Box2) {
        aspect = viewport.size.x / viewport.size.y
    }
}
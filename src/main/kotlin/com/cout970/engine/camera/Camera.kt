package com.cout970.engine.camera

import com.cout970.engine.util.box.Box2
import com.cout970.engine.util.math.rotate
import com.cout970.engine.util.math.toDegrees
import com.cout970.engine.util.math.unaryMinus
import org.joml.Matrix4d
import org.joml.Vector3d

/**
 * Created by cout970 on 30/07/2016.
 */
abstract class Camera {

    var position = Vector3d()
    var rotations = Vector3d()
    var scale = Vector3d(1.0)

    fun getView() = Matrix4d().apply { translate(-position); rotate(-rotations); scale(scale) }

    abstract fun getProjection(): Matrix4d

    override fun toString(): String{
        return "Camera(position=$position, rotations=${rotations.toDegrees()}, scale=$scale)"
    }

    open fun updateViewport(viewport: Box2) {}
}
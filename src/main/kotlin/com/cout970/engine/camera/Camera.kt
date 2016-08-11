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
/**
 * Implementation of basic camera for a 3D space
 * @see OrthoCamera
 * @see PerspectiveCamera
 */
abstract class Camera {

    //The position of the camera
    var position = Vector3d()
    //The rotation in every axis stored in radians
    var rotations = Vector3d()
    //the scale of the 3D space
    var scale = Vector3d(1.0)

    /**
     * Creates a view matrix
     */
    open fun getView() = Matrix4d().apply { translate(-position); rotate(-rotations); scale(scale) }

    /**
     * Creates a projection matrix
     */
    abstract fun getProjection(): Matrix4d

    /**
     * This function allow to adjust the aspect ratio of the projection matrix generated by this camera
     */
    open fun updateViewport(viewport: Box2) {}

    override fun toString(): String{
        return "Camera(position=$position, rotations=${rotations.toDegrees()}, scale=$scale)"
    }
}
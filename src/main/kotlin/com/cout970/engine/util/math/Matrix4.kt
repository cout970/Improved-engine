package com.cout970.engine.util.math

import org.joml.AxisAngle4d
import org.joml.Matrix4d
import org.joml.Quaterniond
import org.joml.Vector3d

/**
 * Created by cout970 on 26/07/2016.
 */
/**
 * Moves the translations of this Matrix4df
 */
fun Matrix4d.translate(vec: Vector3) = setTranslation(getTranslation(Vector3d()) + vec.toMutable())

/**
 * Moves the translations of this Matrix4df
 */
fun Matrix4d.translate(vec: Vector3d) = setTranslation(getTranslation(Vector3d()) + vec)

/**
 * This functions uses the values of the vec as rotations in every axis, x,y,z
 * Rotations are in radians
 */
fun Matrix4d.rotate(vec: Vector3): Matrix4d {
    val quatX = Quaterniond(AxisAngle4d(vec.x, 1.0, 0.0, 0.0))
    val quatY = Quaterniond(AxisAngle4d(vec.y, 0.0, 1.0, 0.0))
    val quatZ = Quaterniond(AxisAngle4d(vec.z, 0.0, 0.0, 1.0))
    return this.rotate(quatX.mul(quatY).mul(quatZ))
}

/**
 * This functions uses the values of the vec as rotations in every axis, x,y,z
 * Rotations are in radians
 */
fun Matrix4d.rotate(vec: Vector3d) = rotate(vec.toImmutable())
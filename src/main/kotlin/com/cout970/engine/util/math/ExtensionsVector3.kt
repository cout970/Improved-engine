package com.cout970.engine.util.math

import com.cout970.engine.util.box.Box3
import org.joml.AxisAngle4d
import org.joml.Matrix3d
import org.joml.Quaterniond
import org.joml.Vector3d

/**
 * Created by cout970 on 08/08/2016.
 */
fun vec3Of(x: Number, y: Number, z: Number) = Vector3(x.toDouble(), y.toDouble(), z.toDouble())

fun Vector3.toMutable() = Vector3d(x, y, z)

infix fun Vector3.to(other: Vector3) = Box3.of(this, other)

operator fun Vector3.plus(v: Vector3d) = Vector3(x + v.x, y + v.y, z + v.z)
operator fun Vector3.minus(v: Vector3d) = Vector3(x - v.x, y - v.y, z - v.z)
operator fun Vector3.times(v: Vector3d) = Vector3(x * v.x, y * v.y, z * v.z)
operator fun Vector3.div(v: Vector3d) = Vector3(x / v.x, y / v.y, z / v.z)

fun Vector3.toRadians() = Vector3d(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z))

fun Vector3.toDegrees() = Vector3d(Math.toDegrees(x), Math.toDegrees(y), Math.toDegrees(z))

fun Vector3.rotate(quat: Quaterniond) = toMutable().rotate(quat).toImmutable()

fun Vector3.rotate(angle: Number, x: Number, y: Number, z: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), x.toDouble(), y.toDouble(), z.toDouble())))

fun Vector3.rotateX(angle: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), 1.0, 0.0, 0.0)))
fun Vector3.rotateY(angle: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), 0.0, 1.0, 0.0)))
fun Vector3.rotateZ(angle: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), 0.0, 0.0, 1.0)))

operator fun Vector3.times(mat: Matrix3d) = Vector3(
        mat.m00 * x + mat.m10 * y + mat.m20 * z,
        mat.m01 * x + mat.m11 * y + mat.m21 * z,
        mat.m02 * x + mat.m12 * y + mat.m22 * z)


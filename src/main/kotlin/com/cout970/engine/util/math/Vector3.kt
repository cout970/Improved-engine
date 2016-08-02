package com.cout970.engine.util.math

import org.joml.AxisAngle4d
import org.joml.Matrix3d
import org.joml.Quaterniond
import org.joml.Vector3d

/**
 * Created by cout970 on 26/07/2016.
 */

fun vec3Of(x:Number, y: Number, z: Number) = Vector3d(x.toDouble(), y.toDouble(), z.toDouble())

fun Vector3d.toRadians() = Vector3d(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z))

fun Vector3d.toDegrees() = Vector3d(Math.toDegrees(x), Math.toDegrees(y), Math.toDegrees(z))

fun Vector3d.rotate(angle: Number, x: Number, y: Number, z: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), x.toDouble(), y.toDouble(), z.toDouble())))

fun Vector3d.rotateX(angle: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), 1.0, 0.0, 0.0)))

fun Vector3d.rotateY(angle: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), 0.0, 1.0, 0.0)))

fun Vector3d.rotateZ(angle: Number) = rotate(Quaterniond(AxisAngle4d(angle.toDouble(), 0.0, 0.0, 1.0)))

fun Vector3d.floor() = Vector3d(Math.floor(x), Math.floor(y), Math.floor(z))
fun Vector3d.ceil() = Vector3d(Math.ceil(x), Math.ceil(y), Math.floor(z))

val Vector3d.xf: Float get() = x.toFloat()
val Vector3d.yf: Float get() = y.toFloat()
val Vector3d.zf: Float get() = z.toFloat()

val Vector3d.xi: Int get() = x.toInt()
val Vector3d.yi: Int get() = y.toInt()
val Vector3d.zi: Int get() = z.toInt()

operator fun Vector3d.plus(v: Number) = Vector3d(x + v.toDouble(), y + v.toDouble(), z + v.toDouble())
operator fun Vector3d.minus(v: Number) = Vector3d(x - v.toDouble(), y - v.toDouble(), z - v.toDouble())
operator fun Vector3d.times(v: Number) = Vector3d(x * v.toDouble(), y * v.toDouble(), z * v.toDouble())
operator fun Vector3d.div(v: Number) = Vector3d(x / v.toDouble(), y / v.toDouble(), z / v.toDouble())

operator fun Vector3d.plus(v: Vector3d) = Vector3d(x + v.x, y + v.y, z + v.z)
operator fun Vector3d.minus(v: Vector3d) = Vector3d(x - v.x, y - v.y, z - v.z)
operator fun Vector3d.times(v: Vector3d) = Vector3d(x * v.x, y * v.y, z * v.z)
operator fun Vector3d.div(v: Vector3d) = Vector3d(x / v.x, y / v.y, z / v.z)

operator fun Vector3d.unaryMinus() = Vector3d(-x, -y, -z)

fun Vector3d.distance(other: Vector3d) = (other - this).length()
fun Vector3d.distanceSq(other: Vector3d) = (other - this).lengthSquared()

operator fun Vector3d.times(m: Matrix3d) = mul(m, Vector3d())!!

fun Vector3d.transform(op: (Double) -> Double) = Vector3d(op(x), op(y), op(z))

fun Vector3d.copy() = Vector3d(this)
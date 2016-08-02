package com.cout970.engine.util.math

import com.cout970.engine.util.box.Box2
import org.joml.Vector2d
import org.joml.Vector3d

/**
 * Created by cout970 on 26/07/2016.
 */

fun Vector2d.toRadians() = Vector2d(Math.toRadians(x), Math.toRadians(y))

fun Vector2d.toDegrees() = Vector2d(Math.toDegrees(x), Math.toDegrees(y))

fun Vector2d.center() = Vector2d(x / 2, y / 2)

fun Vector2d.xCenter() = Vector2d(x / 2, y)
fun Vector2d.yCenter() = Vector2d(x, y / 2)

fun Vector2d.centeredAt(x: Number, y: Number) = Vector2d(x.toDouble(), y.toDouble()) - center()

fun Vector2d.floor() = Vector2d(Math.floor(x), Math.floor(y))
fun Vector2d.ceil() = Vector2d(Math.ceil(x), Math.ceil(y))

infix fun Vector2d.centeredAt(pos: Vector2d) = pos - center()

infix fun Vector2d.to(n: Number) = Vector3d(x, y, n.toDouble())

val Vector2d.xf: Float get() = x.toFloat()
val Vector2d.yf: Float get() = y.toFloat()

val Vector2d.xi: Int get() = x.toInt()
val Vector2d.yi: Int get() = y.toInt()

fun Vector2d.toPair(): Pair<Double, Double> = x to y

fun Vector2d.swap() = Vector2d(y, x)

operator fun Vector2d.plus(v: Number) = Vector2d(v.toDouble() + x, v.toDouble() + y)
operator fun Vector2d.minus(v: Number) = Vector2d(x - v.toDouble(), y - v.toDouble())
operator fun Vector2d.times(v: Number) = Vector2d(v.toDouble() * x, v.toDouble() * y)
operator fun Vector2d.div(v: Number) = Vector2d(x / v.toDouble(), y / v.toDouble())

operator fun Vector2d.plus(v: Vector2d) = Vector2d(v.x + x, v.y + y)
operator fun Vector2d.minus(v: Vector2d) = Vector2d(x - v.x, y - v.y)
operator fun Vector2d.times(v: Vector2d) = Vector2d(v.x * x, v.y * y)
operator fun Vector2d.div(v: Vector2d) = Vector2d(x / v.x, y / v.y)

operator fun Vector2d.unaryMinus() = Vector2d(-x, -y)

fun Vector2d.transform(op: (Double) -> Double) = Vector2d(op(x), op(y))

fun Vector2d.copy() = Vector2d(this)

fun vec2Of(x:Number, y: Number) = Vector2d(x.toDouble(), y.toDouble())

infix fun Vector2d.to(other: Vector2d): Box2 {
    val start = Vector2d(
            Math.min(this.x, other.x),
            Math.min(this.y, other.y)
    )

    val end = Vector2d(
            Math.max(this.x, other.x),
            Math.max(this.y, other.y)
    )
    return Box2.of(start, end)
}
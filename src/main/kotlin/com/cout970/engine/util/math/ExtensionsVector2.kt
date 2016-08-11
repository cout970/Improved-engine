package com.cout970.engine.util.math

import com.cout970.engine.util.box.Box2
import org.joml.Vector2d

/**
 * Created by cout970 on 08/08/2016.
 */

/**
 * Utility function to create a Vector2 with automatic cast to doubles
 */
fun vec2Of(x: Number, y: Number) = Vector2(x.toDouble(), y.toDouble())

/**
 * Creates a new Vector2d from this Vector2
 */
fun Vector2.toMutable() = Vector2d(x, y)

/**
 * Changes the values from degrees to radians
 */
fun Vector2.toRadians() = Vector2(Math.toRadians(x), Math.toRadians(y))

/**
 * Changes the values from radians to degrees
 */
fun Vector2.toDegrees() = Vector2(Math.toDegrees(x), Math.toDegrees(y))


infix fun Vector2.centeredAt(pos: Vector2) = pos - center()

/**
 * Creates a Vector3 from this vector2 and a number
 */
infix fun Vector2.to(n: Number) = Vector3(x, y, n.toDouble())

/**
 * Breaks this vector2 into a pair or doubles
 */
fun Vector2.toPair(): Pair<Double, Double> = x to y

//mutable version of the arithmetic operations
operator fun Vector2.plus(v: Vector2d) = Vector2(v.x + x, v.y + y)
operator fun Vector2.minus(v: Vector2d) = Vector2(x - v.x, y - v.y)
operator fun Vector2.times(v: Vector2d) = Vector2(v.x * x, v.y * y)
operator fun Vector2.div(v: Vector2d) = Vector2(x / v.x, y / v.y)

/**
 * Creates a Box2 using this vector as start and other as end
 */
infix fun Vector2.to(other: Vector2): Box2 {
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
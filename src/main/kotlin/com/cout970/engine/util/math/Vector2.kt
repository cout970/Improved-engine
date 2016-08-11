package com.cout970.engine.util.math

import java.io.Serializable

/**
 * Created by cout970 on 26/07/2016.
 */
/**
 * A Immutable version of Vector2d
 */
data class Vector2(
        val x: Double,
        val y: Double
) : Comparable<Vector2>, Serializable {

    companion object {
        val ORIGIN = vec2Of(0, 0)
        val X_AXIS = vec2Of(1, 0)
        val Y_AXIS = vec2Of(0, 1)
    }

    //basic constructor
    constructor(value: Double) : this(value, value)

    //float version
    val xf: Float get() = x.toFloat()
    val yf: Float get() = y.toFloat()

    //int version
    val xi: Int get() = x.toInt()
    val yi: Int get() = y.toInt()

    //basic math operations
    operator fun plus(v: Number) = Vector2(x + v.toDouble(), y + v.toDouble())
    operator fun minus(v: Number) = Vector2(x - v.toDouble(), y - v.toDouble())
    operator fun times(v: Number) = Vector2(x * v.toDouble(), y * v.toDouble())
    operator fun div(v: Number) = Vector2(x / v.toDouble(), y / v.toDouble())

    operator fun plus(v: Vector2) = Vector2(x + v.x, y + v.y)
    operator fun minus(v: Vector2) = Vector2(x - v.x, y - v.y)
    operator fun times(v: Vector2) = Vector2(x * v.x, y * v.y)
    operator fun div(v: Vector2) = Vector2(x / v.x, y / v.y)

    //utility functions
    override fun compareTo(other: Vector2): Int =
            if (x < other.x) -1 else if (x > other.x) 1 else
                if (y < other.y) -1 else if (y > other.y) 1 else 0

    /**
     * Swaps the values x,y of this vector
     */
    fun swap() = Vector2(y, x)

    /**
     * Gets a vector with the same values as this but negated
     */
    operator fun unaryMinus() = Vector3(-x, -y)

    /**
     * Gets the length, magnitude or norm of this vector
     */
    fun length() = Math.sqrt(lengthSq())

    /**
     * Gets the length, magnitude or norm squared of this vector
     */
    fun lengthSq() = x * x + y * y

    /**
     * Gets the normalized version of this vector
     */
    fun normalize() = this / length()

    /**
     * Gets the dot product of this vector and the other vector
     */
    fun dot(vec: Vector2) = x * vec.x + y * vec.y

    /**
     * Gets the angle in radians between the vectors
     */
    fun angle(vec: Vector2) = Math.acos(angleCos(vec))

    /**
     * Gets the cosine of the angle between this vector and the other vector
     */
    fun angleCos(vec: Vector2): Double {
        var cos = dot(vec) / Math.sqrt(lengthSq() * vec.lengthSq())
        cos = if (cos < 1) cos else 1.0
        cos = if (cos > -1) cos else -1.0
        return cos
    }

    /**
     * Applies the floor function to all the components of this vector
     */
    fun floor() = Vector2(Math.floor(x), Math.floor(y))

    /**
     * Applies the ceil function to all the components of this vector
     */
    fun ceil() = Vector2(Math.ceil(x), Math.ceil(y))

    /**
     * Gets the distance from this vector to the other vector
     */
    fun distance(other: Vector2) = (other - this).length()

    /**
     * Gets the distance squared from this vector to the other vector
     */
    fun distanceSq(other: Vector2) = (other - this).lengthSq()

    /**
     * Applies a transformation to all the components of this vector
     */
    fun transform(op: (Double) -> Double) = Vector2(op(x), op(y))

    /**
     * Gets the middle point between ORIGIN and this vector
     */
    fun Vector2.center() = Vector2(x / 2, y / 2)

    /**
     * Gets the middle point between ORIGIN and this vector in the x axis,
     * the y axis remain the same
     */
    fun Vector2.xCenter() = Vector2(x / 2, y)

    /**
     * Gets the middle point between ORIGIN and this vector in the y axis,
     * the x axis remain the same
     */
    fun Vector2.yCenter() = Vector2(x, y / 2)
}
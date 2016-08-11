package com.cout970.engine.util.math

import java.io.Serializable

/**
 * Created by cout970 on 26/07/2016.
 */

/**
 * A Immutable version of Vector3d
 */
data class Vector3(
        val x: Double,
        val y: Double,
        val z: Double
) : Comparable<Vector3>, Serializable {

    companion object {
        val ORIGIN = vec3Of(0, 0, 0)
        val X_AXIS = vec3Of(1, 0, 0)
        val Y_AXIS = vec3Of(0, 1, 0)
        val Z_AXIS = vec3Of(0, 0, 1)
    }

    //basic constructors
    constructor(value: Double) : this(value, value, value)

    constructor(x: Double, y: Double) : this(x, y, 0.0)

    //float version
    val xf: Float get() = x.toFloat()
    val yf: Float get() = y.toFloat()
    val zf: Float get() = z.toFloat()

    //int version
    val xi: Int get() = x.toInt()
    val yi: Int get() = y.toInt()
    val zi: Int get() = z.toInt()

    //basic math operations
    operator fun plus(v: Number) = Vector3(x + v.toDouble(), y + v.toDouble(), z + v.toDouble())
    operator fun minus(v: Number) = Vector3(x - v.toDouble(), y - v.toDouble(), z - v.toDouble())
    operator fun times(v: Number) = Vector3(x * v.toDouble(), y * v.toDouble(), z * v.toDouble())
    operator fun div(v: Number) = Vector3(x / v.toDouble(), y / v.toDouble(), z / v.toDouble())

    operator fun plus(v: Vector3) = Vector3(x + v.x, y + v.y, z + v.z)
    operator fun minus(v: Vector3) = Vector3(x - v.x, y - v.y, z - v.z)
    operator fun times(v: Vector3) = Vector3(x * v.x, y * v.y, z * v.z)
    operator fun div(v: Vector3) = Vector3(x / v.x, y / v.y, z / v.z)

    //utility functions
    override fun compareTo(other: Vector3): Int =
            if (x < other.x) -1 else if (x > other.x) 1 else
                if (y < other.y) -1 else if (y > other.y) 1 else
                    if (z < other.z) -1 else if (z > other.z) 1 else 0

    /**
     * Gets a vector with the same values as this but negated
     */
    operator fun unaryMinus() = Vector3(-x, -y, -z)

    /**
     * Gets the length, magnitude or norm of this vector
     */
    fun length() = Math.sqrt(lengthSq())

    /**
     * Gets the length, magnitude or norm squared of this vector
     */
    fun lengthSq() = x * x + y * y + z * z

    /**
     * Gets the normalized version of this vector
     */
    fun normalize() = this / length()

    /**
     * Gets the cross product of this vector and the other vector
     */
    fun cross(vec: Vector3) = Vector3(y * vec.z - z * vec.y, z * vec.x - x * vec.z, x * vec.y - y * vec.x)

    /**
     * Gets the dot product of this vector and the other vector
     */
    fun dot(vec: Vector3) = x * vec.x + y * vec.y + z * vec.z

    /**
     * Gets the reflection of this vector on the plane defined by the normal vector
     */
    fun reflect(normal: Vector3): Vector3 {
        val dot2 = dot(normal) * 2
        return Vector3(x - dot2 * normal.x, y - dot2 * normal.y, z - dot2 * normal.z)
    }

    /**
     * Gets the angle in radians between the vectors
     */
    fun angle(vec: Vector3) = Math.acos(angleCos(vec))

    /**
     * Gets the cosine of the angle between this vector and the other vector
     */
    fun angleCos(vec: Vector3): Double {
        var cos = dot(vec) / Math.sqrt(lengthSq() * vec.lengthSq())
        cos = if (cos < 1) cos else 1.0
        cos = if (cos > -1) cos else -1.0
        return cos
    }

    /**
     * Applies the floor function to all the components of this vector
     */
    fun floor() = Vector3(Math.floor(x), Math.floor(y), Math.floor(z))

    /**
     * Applies the ceil function to all the components of this vector
     */
    fun ceil() = Vector3(Math.ceil(x), Math.ceil(y), Math.floor(z))

    /**
     * Gets the distance from this vector to the other vector
     */
    fun distance(other: Vector3) = (other - this).length()

    /**
     * Gets the distance squared from this vector to the other vector
     */
    fun distanceSq(other: Vector3) = (other - this).lengthSq()

    /**
     * Applies a transformation to all the components of this vector
     */
    fun transform(op: (Double) -> Double) = Vector3(op(x), op(y), op(z))
}

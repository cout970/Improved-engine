package com.cout970.engine.util.box

import com.cout970.engine.util.math.*
import org.joml.Vector2d

/**
 * Created by cout970 on 25/07/2016.
 */
data class Box2(
        val pos: Vector2,
        val size: Vector2
) {
    companion object {
        val IDENTITY = Box2(Vector2.ORIGIN, Vector2(1.0))

        fun of(start: Vector2d, end: Vector2d) = Box2(start, end - start)
    }

    //The start of the box
    val start: Vector2 get() = pos
    //The end of the box
    val end: Vector2 get() = pos + size

    constructor(pos: Vector2d, size: Vector2d) : this(pos.toImmutable(), size.toImmutable())

    //translation operations
    operator fun plus(vec: Number) = Box2(pos + vec, size)
    operator fun minus(vec: Number) = Box2(pos - vec, size)

    operator fun plus(vec: Vector2d) = Box2(pos + vec, size)
    operator fun minus(vec: Vector2d) = Box2(pos - vec, size)

    //scale operations
    operator fun times(vec: Number) = Box2(pos, size * vec)
    operator fun div(vec: Number) = Box2(pos, size / vec)

    operator fun times(vec: Vector2d) = Box2(pos, size * vec)
    operator fun div(vec: Vector2d) = Box2(pos, size / vec)

    //utility functions
    operator fun contains(point: Vector2d): Boolean =
            point.x > start.x && point.x < end.x &&
            point.y > start.y && point.y < end.y

    operator fun contains(point: Vector2): Boolean =
            point.x > start.x && point.x < end.x &&
            point.y > start.y && point.y < end.y
}
package com.cout970.engine.util.box

import com.cout970.engine.util.math.div
import com.cout970.engine.util.math.minus
import com.cout970.engine.util.math.plus
import com.cout970.engine.util.math.times
import org.joml.Vector2d
import org.joml.Vector3d

/**
 * Created by cout970 on 25/07/2016.
 */
data class Box2(
        val pos: Vector2d,
        val size: Vector2d
) {

    companion object {
        val IDENTITY = Box2(Vector2d(0.0), Vector2d(1.0))

        fun of(start: Vector2d, end: Vector2d) = Box2(start, end - start)
    }

    val start: Vector2d get() = pos
    val end: Vector2d get() = pos + size

    operator fun plus(vec: Number) = Box2(pos + vec, size)
    operator fun minus(vec: Number) = Box2(pos - vec, size)
    operator fun times(vec: Number) = Box2(pos, size * vec)
    operator fun div(vec: Number) = Box2(pos, size / vec)

    operator fun plus(vec: Vector2d) = Box2(pos + vec, size)
    operator fun minus(vec: Vector2d) = Box2(pos - vec, size)
    operator fun times(vec: Vector2d) = Box2(pos, size * vec)
    operator fun div(vec: Vector2d) = Box2(pos, size / vec)

    operator fun contains(point: Vector3d): Boolean =
            point.x > start.x && point.x < end.x &&
            point.y > start.y && point.y < end.y
}
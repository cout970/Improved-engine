package com.cout970.engine.util.box

import com.cout970.engine.raytrace.IRayObstacle
import com.cout970.engine.raytrace.Ray
import com.cout970.engine.raytrace.RayTraceResult
import com.cout970.engine.raytrace.RayTraceUtil
import com.cout970.engine.util.math.div
import com.cout970.engine.util.math.minus
import com.cout970.engine.util.math.plus
import com.cout970.engine.util.math.times
import org.joml.Vector3d


/**
 * Created by cout970 on 25/07/2016.
 */
data class Box3(
        val pos: Vector3d,
        val size: Vector3d
) : IRayObstacle {

    companion object {
        val IDENTITY = Box3(Vector3d(), Vector3d(1.0, 1.0, 1.0))

        infix fun Vector3d.to(other: Vector3d): Box3 {
            val start = Vector3d(
                    Math.min(this.x, other.x),
                    Math.min(this.y, other.y),
                    Math.min(this.z, other.z)
            )

            val end = Vector3d(
                    Math.max(this.x, other.x),
                    Math.max(this.y, other.y),
                    Math.max(this.z, other.z)
            )
            return of(start, end)
        }

        fun of(start: Vector3d, end: Vector3d) = Box3(start, end - start)
    }

    val start: Vector3d get() = pos
    val end: Vector3d get() = pos + size

    operator fun plus(vec: Number) = Box3(pos + vec, size)
    operator fun minus(vec: Number) = Box3(pos - vec, size)
    operator fun times(vec: Number) = Box3(pos, size * vec)
    operator fun div(vec: Number) = Box3(pos, size / vec)

    operator fun plus(vec: Vector3d) = Box3(pos + vec, size)
    operator fun minus(vec: Vector3d) = Box3(pos - vec, size)
    operator fun times(vec: Vector3d) = Box3(pos, size * vec)
    operator fun div(vec: Vector3d) = Box3(pos, size / vec)

    operator fun contains(point: Vector3d): Boolean =
            point.x > start.x && point.x < end.x &&
            point.y > start.y && point.y < end.y &&
            point.z > start.z && point.z < end.z

    val minX: Double = start.x

    val minY: Double = start.y

    val minZ: Double = start.z

    val maxX: Double = end.x

    val maxY: Double = end.y

    val maxZ: Double = end.z

    override fun rayTrace(ray: Ray): RayTraceResult? = RayTraceUtil.rayTraceBox3(this, ray)
}
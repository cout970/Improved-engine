package com.cout970.engine.raytrace

import com.cout970.engine.util.Direction
import com.cout970.engine.util.box.Box3
import com.cout970.engine.util.math.Vector3

/**
 * Created by cout970 on 31/07/2016.
 */

object RayTraceUtil {

    val EPSILON = 1.0000000116860974E-7

    fun rayTraceBox3(box: Box3, ray: Ray): RayTraceResult? {
        var start = ray.start
        var end = ray.end
        start = start.copy()
        end = end.copy()

        var minX = getIntermediateWithXValue(start, end, box.minX)
        var maxX = getIntermediateWithXValue(start, end, box.maxX)
        var minY = getIntermediateWithYValue(start, end, box.minY)
        var maxY = getIntermediateWithYValue(start, end, box.maxY)
        var minZ = getIntermediateWithZValue(start, end, box.minZ)
        var maxZ = getIntermediateWithZValue(start, end, box.maxZ)

        if (!isVecInsideYZBounds(minX, box)) {
            minX = null
        }

        if (!isVecInsideYZBounds(maxX, box)) {
            maxX = null
        }

        if (!isVecInsideXZBounds(minY, box)) {
            minY = null
        }

        if (!isVecInsideXZBounds(maxY, box)) {
            maxY = null
        }

        if (!isVecInsideXYBounds(minZ, box)) {
            minZ = null
        }

        if (!isVecInsideXYBounds(maxZ, box)) {
            maxZ = null
        }

        var result: Vector3? = null

        if (minX != null) {
            result = minX
        }

        if (maxX != null && (result == null || start.distanceSq(maxX) < start.distanceSq(result))) {
            result = maxX
        }

        if (minY != null && (result == null || start.distanceSq(minY) < start.distanceSq(result))) {
            result = minY
        }

        if (maxY != null && (result == null || start.distanceSq(maxY) < start.distanceSq(result))) {
            result = maxY
        }

        if (minZ != null && (result == null || start.distanceSq(minZ) < start.distanceSq(result))) {
            result = minZ
        }

        if (maxZ != null && (result == null || start.distanceSq(maxZ) < start.distanceSq(result))) {
            result = maxZ
        }

        if (result == null) {
            return null
        } else {
            var side: Direction? = null

            if (result === minX) {
                side = Direction.WEST
            }

            if (result === maxX) {
                side = Direction.EAST
            }

            if (result === minY) {
                side = Direction.DOWN
            }

            if (result === maxY) {
                side = Direction.UP
            }

            if (result === minZ) {
                side = Direction.NORTH
            }

            if (result === maxZ) {
                side = Direction.SOUTH
            }

            return RayTraceResult(ray, result, box)
        }
    }

    fun isVecInsideYZBounds(point: Vector3?, box: Box3): Boolean {
        return point != null && point.y >= box.minY && point.y <= box.maxY && point.z >= box.minZ && point.z <= box.maxZ
    }

    fun isVecInsideXZBounds(point: Vector3?, box: Box3): Boolean {
        return point != null && point.x >= box.minX && point.x <= box.maxX && point.z >= box.minZ && point.z <= box.maxZ
    }

    fun isVecInsideXYBounds(point: Vector3?, box: Box3): Boolean {
        return point != null && point.x >= box.minX && point.x <= box.maxX && point.y >= box.minY && point.y <= box.maxY
    }


    fun getIntermediateWithXValue(start: Vector3, end: Vector3, x: Double): Vector3? {
        val diff = end - start

        if (diff.x * diff.x < EPSILON) {
            return null
        } else {
            val d3 = (x - start.x) / diff.x
            return if (d3 >= 0.0 && d3 <= 1.0) start + (diff * d3) else null
        }
    }

    fun getIntermediateWithYValue(start: Vector3, end: Vector3, y: Double): Vector3? {
        val diff = end - start

        if (diff.y * diff.y < EPSILON) {
            return null
        } else {
            val d3 = (y - start.y) / diff.y
            return if (d3 >= 0.0 && d3 <= 1.0) start + (diff * d3) else null
        }
    }

    fun getIntermediateWithZValue(start: Vector3, end: Vector3, z: Double): Vector3? {
        val diff = end - start

        if (diff.z * diff.z < EPSILON) {
            return null
        } else {
            val d3 = (z - start.z) / diff.z
            return if (d3 >= 0.0 && d3 <= 1.0) start + (diff * d3) else null
        }
    }

}

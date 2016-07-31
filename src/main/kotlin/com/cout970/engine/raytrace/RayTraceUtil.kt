package com.cout970.engine.raytrace

import com.cout970.engine.util.Direction
import com.cout970.engine.util.box.Box3
import com.cout970.engine.util.math.copy
import org.joml.Vector3d

/**
 * Created by cout970 on 31/07/2016.
 */

object RayTraceUtil {

    val EPSILON = 1.0000000116860974E-7

    fun rayTraceBox3(box: Box3, ray: Ray): RayTraceResult? {
        var start = ray.getStart()
        var end = ray.getEnd()
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

        var result: Vector3d? = null

        if (minX != null) {
            result = minX
        }

        if (maxX != null && (result == null || start.distanceSquared(maxX) < start.distanceSquared(result))) {
            result = maxX
        }

        if (minY != null && (result == null || start.distanceSquared(minY) < start.distanceSquared(result))) {
            result = minY
        }

        if (maxY != null && (result == null || start.distanceSquared(maxY) < start.distanceSquared(result))) {
            result = maxY
        }

        if (minZ != null && (result == null || start.distanceSquared(minZ) < start.distanceSquared(result))) {
            result = minZ
        }

        if (maxZ != null && (result == null || start.distanceSquared(maxZ) < start.distanceSquared(result))) {
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

    private fun isVecInsideYZBounds(point: Vector3d?, box: Box3): Boolean {
        return point != null && point.y >= box.minY && point.y <= box.maxY && point.z >= box.minZ && point.z <= box.maxZ
    }

    private fun isVecInsideXZBounds(point: Vector3d?, box: Box3): Boolean {
        return point != null && point.x >= box.minX && point.x <= box.maxX && point.z >= box.minZ && point.z <= box.maxZ
    }

    private fun isVecInsideXYBounds(point: Vector3d?, box: Box3): Boolean {
        return point != null && point.x >= box.minX && point.x <= box.maxX && point.y >= box.minY && point.y <= box.maxY
    }


    private fun getIntermediateWithXValue(start: Vector3d, end: Vector3d, x: Double): Vector3d? {

        val diff = end.copy().sub(start)

        if (diff.x * diff.x < EPSILON) {
            return null
        } else {
            val d3 = (x - start.x) / diff.x
            return if (d3 >= 0.0 && d3 <= 1.0) start.copy().add(diff.mul(d3)) else null
        }
    }

    private fun getIntermediateWithYValue(start: Vector3d, end: Vector3d, y: Double): Vector3d? {

        val diff = end.copy().sub(start)

        if (diff.y * diff.y < EPSILON) {
            return null
        } else {
            val d3 = (y - start.y) / diff.y
            return if (d3 >= 0.0 && d3 <= 1.0) start.copy().add(diff.mul(d3)) else null
        }
    }

    private fun getIntermediateWithZValue(start: Vector3d, end: Vector3d, z: Double): Vector3d? {
        val diff = end.copy().sub(start)

        if (diff.z * diff.z < EPSILON) {
            return null
        } else {
            val d3 = (z - start.z) / diff.z
            return if (d3 >= 0.0 && d3 <= 1.0) start.copy().add(diff.mul(d3)) else null
        }
    }

}

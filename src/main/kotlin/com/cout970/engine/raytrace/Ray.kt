package com.cout970.engine.raytrace

import com.cout970.engine.util.math.Vector3

/**
 * Created by cout970 on 31/07/2016.
 */
/**
 * This data class represent a ray in 3D space
 */
data class Ray(
        val start: Vector3,
        val end: Vector3
){

    /**
     * Gets a point between the start and the end of this ray using linear interpolation
     */
    fun interpolate(percent: Double) = start + (end - start) * percent
}
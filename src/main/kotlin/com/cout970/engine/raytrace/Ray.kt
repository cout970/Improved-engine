package com.cout970.engine.raytrace

import com.cout970.engine.util.math.copy
import org.joml.Vector3d

/**
 * Created by cout970 on 31/07/2016.
 */
data class Ray(
        private val start: Vector3d,
        private val end: Vector3d
) {
    fun getStart() = start.copy()
    fun getEnd() = end.copy()
}
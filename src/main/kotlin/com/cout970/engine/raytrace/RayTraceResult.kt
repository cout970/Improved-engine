package com.cout970.engine.raytrace

import com.cout970.engine.util.math.copy
import org.joml.Vector3d

/**
 * Created by cout970 on 31/07/2016.
 */
class RayTraceResult(
        val ray: Ray,
        private val hit_: Vector3d,
        val target: IRayObstacle
) {
    val hit: Vector3d get() = hit_.copy()

    var extraData: Any? = null
}
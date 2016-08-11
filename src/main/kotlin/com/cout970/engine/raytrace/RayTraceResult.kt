package com.cout970.engine.raytrace

import com.cout970.engine.util.math.Vector3

/**
 * Created by cout970 on 31/07/2016.
 */
/**
 * This class represents the data of a hit between a ray and a IRayObstacle
 */
class RayTraceResult(
        //the ray used to find the IRayObstacle object
        val ray: Ray,
        //the exact point where the ray hits the IRayObstacle object
        val hit: Vector3,
        //the object that collides with the ray
        val target: IRayObstacle
) {
    var extraData: Any? = null
}
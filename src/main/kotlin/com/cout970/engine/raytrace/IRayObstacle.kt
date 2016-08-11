package com.cout970.engine.raytrace

/**
 * Created by cout970 on 31/07/2016.
 */
/**
 * This interface is implemented by objects that can collide with a Ray
 */
interface IRayObstacle {

    /**
     * Checks if the Ray is colliding with this object and returns data about the hit
     */
    fun rayTrace(ray: Ray): RayTraceResult?
}
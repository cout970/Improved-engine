package com.cout970.engine.raytrace

/**
 * Created by cout970 on 31/07/2016.
 */
interface IRayObstacle {

    fun rayTrace(ray: Ray): RayTraceResult?
}
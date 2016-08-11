package com.cout970.engine.util

import com.cout970.engine.util.math.Vector3
import com.cout970.engine.util.math.toMutable
import com.cout970.engine.util.math.vec3Of
import org.joml.Vector3d

/**
 * This class represents an direction in 3D space
 */
enum class Direction(val offset: Vector3, val axis: Direction.Axis, val axisDirection: Direction.AxisDirection) {

    DOWN(vec3Of(0, -1, 0), Axis.Y, AxisDirection.NEGATIVE),
    UP(vec3Of(0, 1, 0), Axis.Y, AxisDirection.POSITIVE),
    NORTH(vec3Of(0, 0, -1), Axis.Z, AxisDirection.NEGATIVE),
    SOUTH(vec3Of(0, 0, 1), Axis.Z, AxisDirection.POSITIVE),
    WEST(vec3Of(-1, 0, 0), Axis.X, AxisDirection.NEGATIVE),
    EAST(vec3Of(1, 0, 0), Axis.X, AxisDirection.POSITIVE);

    //axis values
    val offsetX: Int
        get() = offset.xi

    val offsetY: Int
        get() = offset.yi

    val offsetZ: Int
        get() = offset.zi

    /**
     * Gets the opposite direction
     */
    fun opposite(): Direction {
        return OPPOSITES[ordinal]
    }

    /**
     * Creates a Vector3d with the offset of this direction
     */
    fun toVector3d(): Vector3d {
        return offset.toMutable()
    }


    /**
     * Rotates this direction anti-clockwise
     */
    fun step(axis: Direction): Direction {
        return Direction.getDirection(rotation[axis.ordinal][ordinal])
    }

    /**
     * Rotates this direction around a axis
     */
    fun rotate(axis: Axis, clockwise: Boolean): Direction {
        return step(if (clockwise) axis.negativeDir else axis.positiveDir)
    }

    /**
     * Checks if this direction and other are perpendicular or not
     */
    fun isPerpendicular(dir: Direction): Boolean {
        return !isParallel(dir)
    }

    /**
     * Checks if this direction and other are parallel or not
     */
    fun isParallel(dir: Direction): Boolean {
        return dir.axis == axis
    }

    /**
     * Checks if this direction has the same offset that the given vector3
     */
    fun matches(offset: Vector3): Boolean {
        return this.offset == offset
    }

    /**
     * This class represent and Axis in the 3D space in cartesian coordinates
     */
    enum class Axis(private val negative: Int) {
        X(4),
        Y(0),
        Z(2);

        val positiveDir: Direction
            get() = getDirection(negative).opposite()

        val negativeDir: Direction
            get() = getDirection(negative)

        fun getDirectionByAxisDirection(a: AxisDirection): Direction {
            return if (a == AxisDirection.POSITIVE) positiveDir else negativeDir
        }

        val parallelDirections: Array<Direction>
            get() = arrayOf(negativeDir, positiveDir)

        val perpendicularDirections: Array<Direction>
            get() {
                val dirs = Array(4, { NORTH })
                var index = 0
                Direction.values().filter { it.axis != this  }.forEach { dirs[index++] = it }
                return dirs
            }
    }

    /**
     * This class represents the two possible values for a direction in an Axis
     */
    enum class AxisDirection(val value: Int) {
        POSITIVE(1),
        NEGATIVE(-1);

        fun getDirectionByAxis(a: Axis): Direction {
            return if (this == POSITIVE) a.positiveDir else a.negativeDir
        }
    }

    companion object {

        val OPPOSITES = arrayOf(UP, DOWN, SOUTH, NORTH, EAST, WEST)
        val HORIZONTAL = arrayOf(NORTH, SOUTH, WEST, EAST)

        //all possible rotations of directions
        val rotation = arrayOf(
                intArrayOf(0, 1, 5, 4, 2, 3),
                intArrayOf(0, 1, 4, 5, 3, 2),
                intArrayOf(5, 4, 2, 3, 0, 1),
                intArrayOf(4, 5, 2, 3, 1, 0),
                intArrayOf(2, 3, 1, 0, 4, 5),
                intArrayOf(3, 2, 0, 1, 4, 5),
                intArrayOf(0, 1, 2, 3, 4, 5))

        fun getDirection(i: Int): Direction {
            return values()[i % values().size]
        }
    }
}

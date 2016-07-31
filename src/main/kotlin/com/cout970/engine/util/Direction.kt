package com.cout970.engine.util

import com.cout970.engine.util.math.copy
import com.cout970.engine.util.math.xi
import com.cout970.engine.util.math.yi
import com.cout970.engine.util.math.zi
import org.joml.Vector3d

enum class Direction(x: Int, y: Int, z: Int, val axis: Direction.Axis, val axisDirection: Direction.AxisDirection) {

    DOWN(0, -1, 0, Axis.Y, AxisDirection.NEGATIVE),
    UP(0, 1, 0, Axis.Y, AxisDirection.POSITIVE),
    NORTH(0, 0, -1, Axis.Z, AxisDirection.NEGATIVE),
    SOUTH(0, 0, 1, Axis.Z, AxisDirection.POSITIVE),
    WEST(-1, 0, 0, Axis.X, AxisDirection.NEGATIVE),
    EAST(1, 0, 0, Axis.X, AxisDirection.POSITIVE);

    private val offsets: Vector3d

    init {
        offsets = Vector3d(x.toDouble(), y.toDouble(), z.toDouble())
    }

    val offsetX: Int
        get() = offsets.xi

    val offsetY: Int
        get() = offsets.yi

    val offsetZ: Int
        get() = offsets.zi

    fun opposite(): Direction {
        return OPPOSITES[ordinal]
    }

    fun toVector3(): Vector3d {
        return offsets.copy()
    }

    //anti-clockwise
    fun step(axis: Direction): Direction {
        return Direction.getDirection(rotation[axis.ordinal][ordinal])
    }

    fun rotate(axis: Axis, clockwise: Boolean): Direction {
        return step(if (clockwise) axis.negativeDir else axis.positiveDir)
    }

    fun isPerpendicular(dir: Direction): Boolean {
        return !isParallel(dir)
    }

    fun isParallel(dir: Direction): Boolean {
        return dir.axis == axis
    }

    fun matches(offset: Vector3d): Boolean {
        return offsets == offset
    }

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
                for (dir in Direction.values()) {
                    if (dir.axis != this) {
                        dirs[index] = dir
                        index++
                    }
                }
                return dirs
            }
    }

    enum class AxisDirection(private val direction: Int) {
        POSITIVE(1),
        NEGATIVE(-1);

        fun getDirectionByAxis(a: Axis): Direction {
            return if (this == POSITIVE) a.positiveDir else a.negativeDir
        }
    }

    companion object {

        val VALID_DIRECTIONS = arrayOf(DOWN, UP, NORTH, SOUTH, WEST, EAST)
        val OPPOSITES = arrayOf(UP, DOWN, SOUTH, NORTH, EAST, WEST)
        val HORIZONTAL = arrayOf(NORTH, SOUTH, WEST, EAST)

        val rotation = arrayOf(
                intArrayOf(0, 1, 5, 4, 2, 3),
                intArrayOf(0, 1, 4, 5, 3, 2),
                intArrayOf(5, 4, 2, 3, 0, 1),
                intArrayOf(4, 5, 2, 3, 1, 0),
                intArrayOf(2, 3, 1, 0, 4, 5),
                intArrayOf(3, 2, 0, 1, 4, 5),
                intArrayOf(0, 1, 2, 3, 4, 5))

        fun getDirection(i: Int): Direction {
            return values()[i % VALID_DIRECTIONS.size]
        }
    }
}

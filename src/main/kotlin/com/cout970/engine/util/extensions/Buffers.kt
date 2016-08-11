package com.cout970.engine.util.extensions

import com.cout970.engine.util.math.Vector2
import com.cout970.engine.util.math.Vector3
import org.lwjgl.BufferUtils
import java.nio.ByteBuffer

/**
 * Created by cout970 on 08/08/2016.
 */

/**
 * Expands the size of a buffer
 */
fun ByteBuffer.expand(newSize: Int): ByteBuffer {
    this.flip()
    return BufferUtils.createByteBuffer(newSize).put(this)
}

/**
 * Reads a Vector3 from the buffer
 */
fun ByteBuffer.readVector3() = Vector3(double, double, double)

/**
 * Writes a Vector3 from the buffer
 */
fun ByteBuffer.writeVector3(vec: Vector3) {
    putDouble(vec.x); putDouble(vec.y); putDouble(vec.z)
}

/**
 * Reads a Vector2 from the buffer
 */
fun ByteBuffer.readVector2() = Vector2(double, double)

/**
 * Writes a Vector2 from the buffer
 */
fun ByteBuffer.writeVector2(vec: Vector2) {
    putDouble(vec.x); putDouble(vec.y);
}
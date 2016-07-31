package com.cout970.engine.util

import org.lwjgl.BufferUtils
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.channels.Channels

/**
 * Created by cout970 on 29/07/2016.
 */

fun InputStream.toByteBuffer(): ByteBuffer {

    var buffer = BufferUtils.createByteBuffer(1024)
    val rbc = Channels.newChannel(this)
    while (true) {
        val r = rbc.read(buffer)
        if (r == -1) break
        if (buffer.remaining() == 0) {
            buffer = buffer.expand(buffer.capacity() * 2)
        }
    }
    rbc.close()
    return buffer

}

fun ByteBuffer.expand(newSize: Int): ByteBuffer {
    this.flip()
    return BufferUtils.createByteBuffer(newSize).put(this)
}
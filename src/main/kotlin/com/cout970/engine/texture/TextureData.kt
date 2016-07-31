package com.cout970.engine.texture

import com.cout970.engine.resource.Resource
import org.joml.Vector2d
import java.nio.ByteBuffer

/**
 * Created by cout970 on 29/07/2016.
 */
data class TextureData(
        val bitMap: ByteBuffer,
        val size: Vector2d,
        val byteDepth: Int,
        val resource: Resource
)
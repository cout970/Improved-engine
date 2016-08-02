package com.cout970.engine.shader

import com.cout970.engine.util.math.xf
import com.cout970.engine.util.math.yf
import com.cout970.engine.util.math.zf
import org.joml.Matrix4d
import org.joml.Vector2d
import org.joml.Vector3d
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20.*

/**
 * Created by cout970 on 30/07/2016.
 */
open class UniformVariable(
        val uniformID: Int
) {

    fun setFloat(f: Float) = glUniform1f(uniformID, f)

    fun setInt(i: Int) = glUniform1i(uniformID, i)

    fun setVector2(f: Vector2d) = glUniform2f(uniformID, f.xf, f.yf)

    fun setVector3(f: Vector3d) = glUniform3f(uniformID, f.xf, f.yf, f.zf)

    fun setBoolean(bool: Boolean) = glUniform1f(uniformID, (if (bool) 1 else 0).toFloat())

    fun setMatrix4(matrix: Matrix4d) {
        val buffer = BufferUtils.createFloatBuffer(16)
        matrix.get(buffer)
        glUniformMatrix4fv(uniformID, false, buffer)
    }

    class UniformVariableArray(uniformID: Int) : UniformVariable(uniformID) {

        fun setFloat(index: Int, f: Float) = glUniform1f(uniformID + index, f)

        fun setInt(index: Int, i: Int) = glUniform1i(uniformID + index, i)

        fun setVector2(index: Int, f: Vector2d) = glUniform2f(uniformID + index, f.xf, f.yf)

        fun setVector3(index: Int, f: Vector3d) = glUniform3f(uniformID + index, f.xf, f.yf, f.zf)

        fun setBoolean(index: Int, bool: Boolean) = glUniform1f(uniformID + index, (if (bool) 1 else 0).toFloat())

        fun setMatrix4(index: Int, matrix: Matrix4d) {
            val buffer = BufferUtils.createFloatBuffer(16)
            matrix.get(buffer)
            glUniformMatrix4fv(uniformID + index, false, buffer)
        }
    }
}
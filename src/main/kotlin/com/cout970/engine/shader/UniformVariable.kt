package com.cout970.engine.shader

import com.cout970.engine.util.math.*
import org.joml.Matrix4d
import org.joml.Vector2d
import org.joml.Vector3d
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20.*

/**
 * Created by cout970 on 30/07/2016.
 */
/**
 * This class represents a variable inside a shader
 */
open class UniformVariable(
        val uniformID: Int
) {

    /**
     * Stores a float in the variable
     */
    fun setFloat(f: Float) = glUniform1f(uniformID, f)

    /**
     * Stores a int in the variable
     */
    fun setInt(i: Int) = glUniform1i(uniformID, i)

    /**
     * Stores a Vector2 in the variable
     */
    fun setVector2(f: Vector2) = glUniform2f(uniformID, f.xf, f.yf)

    /**
     * Stores a Vector2d in the variable
     */
    fun setVector2(f: Vector2d) = glUniform2f(uniformID, f.xf, f.yf)

    /**
     * Stores a Vector3d in the variable
     */
    fun setVector3(f: Vector3d) = glUniform3f(uniformID, f.xf, f.yf, f.zf)

    /**
     * Stores a Vector3 in the variable
     */
    fun setVector3(f: Vector3) = glUniform3f(uniformID, f.xf, f.yf, f.zf)

    /**
     * Stores a 1 or 0 in the variable
     */
    fun setBoolean(bool: Boolean) = glUniform1f(uniformID, if (bool) 1f else 0f)

    /**
     * Stores a Matrix4d in the variable
     */
    fun setMatrix4(matrix: Matrix4d) {
        val buffer = BufferUtils.createFloatBuffer(16)
        matrix.get(buffer)
        glUniformMatrix4fv(uniformID, false, buffer)
    }

    class UniformVariableArray(uniformID: Int) : UniformVariable(uniformID) {

        fun setFloat(index: Int, f: Float) = glUniform1f(uniformID + index, f)

        fun setInt(index: Int, i: Int) = glUniform1i(uniformID + index, i)

        fun setVector2(index: Int, f: Vector2) = glUniform2f(uniformID + index, f.xf, f.yf)
        fun setVector2(index: Int, f: Vector2d) = glUniform2f(uniformID + index, f.xf, f.yf)

        fun setVector3(index: Int, f: Vector3) = glUniform3f(uniformID + index, f.xf, f.yf, f.zf)
        fun setVector3(index: Int, f: Vector3d) = glUniform3f(uniformID + index, f.xf, f.yf, f.zf)

        fun setBoolean(index: Int, bool: Boolean) = glUniform1f(uniformID + index, (if (bool) 1 else 0).toFloat())

        fun setMatrix4(index: Int, matrix: Matrix4d) {
            val buffer = BufferUtils.createFloatBuffer(16)
            matrix.get(buffer)
            glUniformMatrix4fv(uniformID + index, false, buffer)
        }
    }
}
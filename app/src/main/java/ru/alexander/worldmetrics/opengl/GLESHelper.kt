package ru.alexander.worldmetrics.opengl

import android.opengl.GLES20
import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class GLESHelper private constructor() {
    companion object {
        const val BYTES_PER_VERTEX = 4
        const val COORDS_PER_VERTEX_2D = 2


        const val VARIABLE_POSITION = "vPosition"
        const val VARIABLE_COLOR = "vColor"

        fun loadShader(type: Int, shaderCode: String): Int {
            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
            return GLES20.glCreateShader(type).also { shader ->
                // add the source code to the shader and compile it
                GLES20.glShaderSource(shader, shaderCode)
                GLES20.glCompileShader(shader)
            }
        }

        fun createProgram(vertexShaderText: String, fragmentShaderText: String): Int {
            val vertexShader: Int = if (vertexShaderText.length > 0) loadShader(
                GLES20.GL_VERTEX_SHADER,
                vertexShaderText
            ) else -1
            val fragmentShader: Int = if (fragmentShaderText.length > 0) loadShader(
                GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderText
            ) else -1

            // create empty OpenGL ES Program
            return GLES20.glCreateProgram().also {
                // add the vertex shader to program
                if (vertexShader > 0)
                    GLES20.glAttachShader(it, vertexShader)

                // add the fragment shader to program
                if (fragmentShader > 0)
                    GLES20.glAttachShader(it, fragmentShader)

                // creates OpenGL ES program executables
                GLES20.glLinkProgram(it)
            }
        }

        fun checkGlError(op: String, tag: String) {
            var error: Int
            while (GLES20.glGetError().also { error = it } != GLES20.GL_NO_ERROR) {
                Log.e(tag, "$op: glError $error")
                throw java.lang.RuntimeException("$op: glError $error")
            }
        }

        fun coordsToByteBuffer(coords: FloatArray, bytesPerVertex: Int): FloatBuffer {
            return ByteBuffer.allocateDirect(coords.size * bytesPerVertex).run {
                // use the device hardware's native byte order
                order(ByteOrder.nativeOrder())

                // create a floating point buffer from the ByteBuffer
                asFloatBuffer().apply {
                    // add the coordinates to the FloatBuffer
                    put(coords)
                    // set the buffer to read the first coordinate
                    position(0)
                }
            }
        }
    }
}
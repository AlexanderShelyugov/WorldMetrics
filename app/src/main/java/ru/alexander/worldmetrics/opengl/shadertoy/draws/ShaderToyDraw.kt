package ru.alexander.worldmetrics.opengl.shadertoy.draws

import android.opengl.GLES20
import ru.alexander.worldmetrics.global.AssetsContainer.Companion.openRawResource
import ru.alexander.worldmetrics.opengl.PlaybackTimer
import ru.alexander.worldmetrics.opengl.GLESHelper.Companion.BYTES_PER_VERTEX
import ru.alexander.worldmetrics.opengl.GLESHelper.Companion.COORDS_PER_VERTEX_2D
import ru.alexander.worldmetrics.opengl.GLESHelper.Companion.VARIABLE_POSITION
import ru.alexander.worldmetrics.opengl.GLESHelper.Companion.coordsToByteBuffer
import ru.alexander.worldmetrics.opengl.RedrawCountHelper
import ru.alexander.worldmetrics.opengl.shadertoy.ShaderToyHelper.Companion.VARIABLE_RESOLUTION
import ru.alexander.worldmetrics.opengl.shadertoy.ShaderToyHelper.Companion.VARIABLE_TIME
import ru.alexander.worldmetrics.opengl.shadertoy.ShaderToyHelper.Companion.createOpenGLESProgram

class ShaderToyDraw(shaderId: Int) {
    private companion object {
        val POSITION = floatArrayOf(
            -1.0f, -1.0f,
            1.0f, -1.0f,
            -1.0f, 1.0f,
            1.0f, 1.0f,
        )

        val POSITION_BUFFER = coordsToByteBuffer(POSITION, BYTES_PER_VERTEX)
        val VERTEX_COUNT = POSITION.size / COORDS_PER_VERTEX_2D
        const val VERTEX_STRIDE = COORDS_PER_VERTEX_2D * BYTES_PER_VERTEX
        const val OPTIMIZATION_METHOD = GLES20.GL_STATIC_DRAW
    }

    private val mProgram: Int = createOpenGLESProgram(openRawResource(shaderId))
    private val buffer = IntArray(1)
    private val playbackTimer = PlaybackTimer()

    init {
        GLES20.glGenBuffers(1, buffer, 0)
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffer[0])
        GLES20.glBufferData(
            GLES20.GL_ARRAY_BUFFER,
            POSITION_BUFFER.capacity() * BYTES_PER_VERTEX,
            POSITION_BUFFER,
            OPTIMIZATION_METHOD
        )
    }

    fun draw(width: Int, height: Int) {
        val playbackTime = playbackTimer.check()

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram)
        GLES20.glGetAttribLocation(mProgram, VARIABLE_POSITION).also {
            // Enable a handle to the triangle vertices
            GLES20.glEnableVertexAttribArray(it)

            GLES20.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX_2D,
                GLES20.GL_FLOAT,
                false,
                VERTEX_STRIDE,
                0
            )

            GLES20.glGetUniformLocation(mProgram, VARIABLE_TIME).also { handle ->
                GLES20.glUniform1f(handle, playbackTime)
            }

            GLES20.glGetUniformLocation(mProgram, VARIABLE_RESOLUTION).also { handle ->
                GLES20.glUniform3f(handle, width.toFloat(), height.toFloat(), 0f)
            }

            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, VERTEX_COUNT)

            GLES20.glDisableVertexAttribArray(it)
        }
        RedrawCountHelper.triggerRedraw()
    }
}
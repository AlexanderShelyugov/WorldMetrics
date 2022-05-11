package ru.alexander.worldmetrics.views.opengl

import android.opengl.GLES20
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.AssetsContainer.Companion.openRawResource
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.BYTES_PER_VERTEX
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.coordsToByteBuffer
import java.util.*
import java.util.concurrent.TimeUnit.MILLISECONDS

class Smoke {
    private companion object {
        const val TIME_VARIABLE = "iTime"
        const val RESOLUTION_VARIABLE = "iResolution"
        const val POSITION_VARIABLE = "vPosition"

        const val COORDS_PER_VERTEX = 2

        val POSITION = floatArrayOf(
            -1.0f, -1.0f,
            1.0f, -1.0f,
            -1.0f, 1.0f,
            1.0f, 1.0f,
        )

        val POSITION_BUFFER = coordsToByteBuffer(POSITION, BYTES_PER_VERTEX)

        val VERTEX_COUNT: Int = POSITION.size / COORDS_PER_VERTEX
        val VERTEX_STRIDE: Int = COORDS_PER_VERTEX * BYTES_PER_VERTEX

        val OPTIMIZATION_METHOD: Int = GLES20.GL_STATIC_DRAW
    }


    private val mProgram: Int = GLESHelper.createProgram(
        openRawResource(R.raw.simple_vertex_shader),
        openRawResource(R.raw.smoke_with_lights)
    )

    //    private var positionBuffer:
    private var beginTime: Date? = null
    private val buffer = IntArray(1)

    init {
        GLES20.glGenBuffers(1, buffer, 0)
    }

    fun draw(width: Int, height: Int) {
        if (beginTime == null) {
            beginTime = Date()
        }

        val diff = Date().time - beginTime!!.time
        val playbackTime: Float = MILLISECONDS.toSeconds(diff).toFloat()

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffer[0]);
        GLES20.glBufferData(
            GLES20.GL_ARRAY_BUFFER,
            POSITION_BUFFER.capacity() * BYTES_PER_VERTEX,
            POSITION_BUFFER,
            OPTIMIZATION_METHOD
        );

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram)
        GLES20.glGetAttribLocation(mProgram, POSITION_VARIABLE).also {
            // Enable a handle to the triangle vertices
            GLES20.glEnableVertexAttribArray(it)

            GLES20.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                VERTEX_STRIDE,
                0
            )

            GLES20.glGetUniformLocation(mProgram, TIME_VARIABLE).also { handle ->
                GLES20.glUniform1f(handle, playbackTime)
            }

            GLES20.glGetUniformLocation(mProgram, RESOLUTION_VARIABLE).also { handle ->
                GLES20.glUniform3f(handle, width.toFloat(), height.toFloat(), 0f)
            }

            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, VERTEX_COUNT)

            GLES20.glDisableVertexAttribArray(it)
        }
    }
}
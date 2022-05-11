package ru.alexander.worldmetrics.views.opengl

import android.opengl.GLES20
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.AssetsContainer.Companion.openRawResource
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.BYTES_PER_VERTEX
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.COORDS_PER_VERTEX_2D
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.VARIABLE_POSITION
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.coordsToByteBuffer
import ru.alexander.worldmetrics.views.opengl.ShaderToyHelper.Companion.VARIABLE_RESOLUTION
import ru.alexander.worldmetrics.views.opengl.ShaderToyHelper.Companion.VARIABLE_TIME
import ru.alexander.worldmetrics.views.opengl.ShaderToyHelper.Companion.createOpenGLESProgram
import java.util.*
import java.util.concurrent.TimeUnit.MILLISECONDS

class Smoke {
    private companion object {

        val POSITION = floatArrayOf(
            -1.0f, -1.0f,
            1.0f, -1.0f,
            -1.0f, 1.0f,
            1.0f, 1.0f,
        )

        val POSITION_BUFFER = coordsToByteBuffer(POSITION, BYTES_PER_VERTEX)

        val VERTEX_COUNT: Int = POSITION.size / COORDS_PER_VERTEX_2D
        const val VERTEX_STRIDE: Int = COORDS_PER_VERTEX_2D * BYTES_PER_VERTEX
        const val OPTIMIZATION_METHOD: Int = GLES20.GL_STATIC_DRAW
    }

    private val mProgram: Int = createOpenGLESProgram(openRawResource(R.raw.smoke_with_lights))

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

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffer[0])
        GLES20.glBufferData(
            GLES20.GL_ARRAY_BUFFER,
            POSITION_BUFFER.capacity() * BYTES_PER_VERTEX,
            POSITION_BUFFER,
            OPTIMIZATION_METHOD
        )

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
    }
}
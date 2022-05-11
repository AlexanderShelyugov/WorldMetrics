package ru.alexander.worldmetrics.views.opengl

import android.opengl.GLES20
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.AssetsContainer.Companion.openRawResource
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.BYTES_PER_VERTEX
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.COORDS_PER_VERTEX_2D
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.VARIABLE_COLOR
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.VARIABLE_POSITION
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.coordsToByteBuffer
import ru.alexander.worldmetrics.views.opengl.GLESHelper.Companion.createProgram
import java.nio.FloatBuffer

class Triangle {

    // number of coordinates per vertex in this array
    private val triangleCoords = floatArrayOf(
        // in counterclockwise order:
        0.0f, 0.622008459f,      // top
        -0.5f, -0.311004243f,    // bottom left
        0.5f, -0.311004243f,      // bottom right
    )

    // Set color with red, green, blue and alpha (opacity) values
    private val color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

    private val vertexBuffer: FloatBuffer = coordsToByteBuffer(triangleCoords, BYTES_PER_VERTEX)

    private var mProgram: Int

    private var positionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val vertexCount: Int = triangleCoords.size / COORDS_PER_VERTEX_2D
    private val vertexStride: Int = COORDS_PER_VERTEX_2D * BYTES_PER_VERTEX // 4 bytes per vertex

    init {
        mProgram = createProgram(
            openRawResource(R.raw.simple_vertex_shader),
            openRawResource(R.raw.simple_fragment_shader)
        )
    }

    fun draw() {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram)

        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, VARIABLE_POSITION).also {

            // Enable a handle to the triangle vertices
            GLES20.glEnableVertexAttribArray(it)

            // Prepare the triangle coordinate data
            GLES20.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX_2D,
                GLES20.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )

            // get handle to fragment shader's vColor member
            mColorHandle = GLES20.glGetUniformLocation(mProgram, VARIABLE_COLOR).also { colorHandle ->

                // Set color for drawing the triangle
                GLES20.glUniform4fv(colorHandle, 1, color, 0)
            }

            // Draw the triangle
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

            // Disable vertex array
            GLES20.glDisableVertexAttribArray(it)
        }
    }
}

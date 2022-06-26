package ru.alexander.worldmetrics.opengl.shadertoy.renderer

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import ru.alexander.worldmetrics.opengl.shadertoy.draws.ShaderToyDraw
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class ShaderToyRenderer(shaderId: Int) : GLSurfaceView.Renderer {
    private val draw = ShaderToyDraw(shaderId)
    private var width: Int = 0
    private var height: Int = 0

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.5f, 1.0f)
        GLES20.glEnable(GLES20.GL_CULL_FACE)
        GLES20.glCullFace(GLES20.GL_BACK)
        draw.init()
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        this.width = width
        this.height = height
    }

    override fun onDrawFrame(unused: GL10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        draw.draw(width, height)
    }
}

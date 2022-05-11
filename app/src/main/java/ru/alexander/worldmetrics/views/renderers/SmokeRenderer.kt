package ru.alexander.worldmetrics.views.renderers

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import ru.alexander.worldmetrics.views.opengl.Smoke
import ru.alexander.worldmetrics.views.opengl.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class SmokeRenderer : GLSurfaceView.Renderer {
    private lateinit var mTriangle: Triangle
    private lateinit var mSmoke: Smoke
    private var width: Int = 0
    private var height: Int = 0

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.5f, 1.0f)
        // initialize a triangle
        mTriangle = Triangle()
        mSmoke = Smoke()
        GLES20.glEnable(GLES20.GL_CULL_FACE)
        GLES20.glCullFace(GLES20.GL_BACK)
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        this.width = width
        this.height = height
    }

    override fun onDrawFrame(unused: GL10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        mSmoke.draw(width, height)
//        mTriangle.draw()
    }
}

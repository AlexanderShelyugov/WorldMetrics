package ru.alexander.worldmetrics.views

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import ru.alexander.worldmetrics.views.renderers.SmokeRenderer

class SmokeView(context: Context, attributes: AttributeSet) : GLSurfaceView(context, attributes) {
    init {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(SmokeRenderer())
    }
}
package ru.alexander.worldmetrics.views

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.util.Log
import android.widget.Toast
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.views.renderers.SmokeRenderer


class OpenGLView(context: Context, attributes: AttributeSet) : GLSurfaceView(context, attributes) {
    private val renderer = SmokeRenderer()

    init {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)
        // Set the Renderer for drawing on the GLSurfaceView
        context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.OpenGLView,
            0, 0
        ).apply {
            try {
//                val rendererClassName = getString(R.styleable.OpenGLView_renderer)
//                val rendererClass = Class.forName(rendererClassName!!)
//                val ctor = rendererClass.getConstructor()
//                val renderer2 = ctor.newInstance() as Renderer
//                Toast.makeText(context, renderer.toString(), Toast.LENGTH_LONG).show()
                setRenderer(renderer)
            } catch (e: Exception) {
                Log.e("!!!!!!!!!!!!!", e.message!!)
            } finally {
                recycle()
            }
        }
    }
}
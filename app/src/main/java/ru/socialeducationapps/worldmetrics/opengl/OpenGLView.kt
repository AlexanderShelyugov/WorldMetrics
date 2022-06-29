package ru.socialeducationapps.worldmetrics.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.opengl.shadertoy.ShaderToyHelper.Companion.bindToShader


class OpenGLView(context: Context, attributes: AttributeSet) : GLSurfaceView(context, attributes) {
    init {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)
        context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.OpenGLView,
            0, 0
        ).apply {
            try {
                val shaderId = this.getResourceId(R.styleable.OpenGLView_shader, -1)
                if (0 < shaderId) {
                    bindToShader(this@OpenGLView, shaderId)
                }
            } finally {
                recycle()
            }
        }
    }
}
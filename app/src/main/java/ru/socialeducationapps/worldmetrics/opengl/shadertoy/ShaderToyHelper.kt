package ru.socialeducationapps.worldmetrics.opengl.shadertoy

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.global.AssetsContainer.Companion.openRawResource
import ru.socialeducationapps.worldmetrics.opengl.GLESHelper.Companion.createProgram
import ru.socialeducationapps.worldmetrics.opengl.OpenGLView
import ru.socialeducationapps.worldmetrics.opengl.shadertoy.renderer.ShaderToyRenderer

class ShaderToyHelper private constructor() {
    companion object {
        const val VARIABLE_TIME = "iTime"
        const val VARIABLE_FRAME = "iFrame"
        const val VARIABLE_RESOLUTION = "iResolution"

        private const val SHADER_PREFIX = "precision highp float;\n" +
                "\n" +
                "uniform vec3      iResolution;// viewport resolution (in pixels)\n" +
                "uniform float     iTime;// shader playback time (in seconds)\n" +
                "uniform float     iTimeDelta;// render time (in seconds)\n" +
                "uniform int       iFrame;// shader playback frame\n" +
                "uniform float     iChannelTime[4];// channel playback time (in seconds)\n" +
                "uniform vec3      iChannelResolution[4];// channel resolution (in pixels)\n" +
                "uniform vec4      iMouse;// mouse pixel coords. xy: current (if MLB down), zw: click\n" +
                "uniform vec4      iDate;// (year, month, day, time in seconds)\n" +
                "uniform float     iSampleRate;// sound sample rate (i.e., 44100)\n"

        private const val SHADER_MAIN_FUNCTION = "\n" +
                "void main() {\n" +
                "    mainImage(gl_FragColor, gl_FragCoord.xy);\n" +
                "}"

        fun bindToShader(view: OpenGLView, shaderId: Int) {
            val renderer = ShaderToyRenderer(shaderId)
            view.setRenderer(renderer)
        }

        fun createOpenGLESProgram(shaderToy: String): Int {
            return createProgram(
                openRawResource(R.raw.simple_vertex_shader),
                createFragmentShader(shaderToy)
            )
        }

        private fun createFragmentShader(shaderToy: String): String {
            return SHADER_PREFIX + shaderToy + SHADER_MAIN_FUNCTION
        }
    }
}
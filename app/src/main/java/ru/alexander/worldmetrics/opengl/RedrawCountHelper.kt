package ru.alexander.worldmetrics.opengl

import android.app.ActivityManager
import android.content.Context.ACTIVITY_SERVICE
import ru.alexander.worldmetrics.global.ContextAccess.Companion.context
import java.lang.Runtime.getRuntime
import java.util.*

class RedrawCountHelper private constructor() {
    companion object {
        private var redrawCount: Int = 0
        private var prevDrawTime: Date? = null

        private val activityManager get() = context.getSystemService(ACTIVITY_SERVICE)!! as ActivityManager

        fun triggerRedraw() {
            redrawCount++
            val duration = prevDrawTime?.let {
                val now = Date()
                val duration = now.time - it.time
                prevDrawTime = now
                duration
            }

            prevDrawTime = Date()
            /*
            Handler(Looper.getMainLooper()).post {
                var textView: TextView = activity?.findViewById(R.id.tv_debug_information)!!
                textView?.text = "RAM size: ${getRamSize()}\n" +
                        "Heap size: ${getHeapSize()}\n" +
                        "Currently used: ${getUsedHeapSize()} (${getUsedHeapSize().toFloat() * 100F / getHeapSize()}%)\n" +
                        "OpenGL version: ${getOpenGLVersion()}"

                textView = activity?.findViewById(R.id.tv_redraw_count)!!
                textView?.text = "Redraw count: $redrawCount\n" +
                        "Redraw took: $duration ms"
            }
             */
        }

        private fun getRamSize(): Long {
            val memInfo = ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(memInfo)
            return memInfo.totalMem
        }

        private fun getHeapSize(): Long {
            val runtime = getRuntime()
            return runtime.maxMemory()
        }

        private fun getUsedHeapSize(): Long {
            return getHeapSize() - getRuntime().freeMemory()
        }

        private fun getOpenGLVersion(): String {
            val configurationInfo = activityManager.deviceConfigurationInfo
            return configurationInfo.glEsVersion
        }

    }
}
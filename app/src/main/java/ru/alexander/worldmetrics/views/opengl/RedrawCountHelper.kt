package ru.alexander.worldmetrics.views.opengl

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import ru.alexander.worldmetrics.R
import java.util.*

class RedrawCountHelper {
    companion object {
        var activity: Activity? = null
        private var redrawCount: Int = 0
        private var prevDrawTime: Date? = null

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
            val actManager: ActivityManager =
                activity?.getSystemService(Context.ACTIVITY_SERVICE)!! as ActivityManager
            val memInfo = ActivityManager.MemoryInfo()
            actManager.getMemoryInfo(memInfo)
            return memInfo.totalMem
        }

        private fun getHeapSize(): Long {
            val runtime = Runtime.getRuntime()
            return runtime.maxMemory()
        }

        private fun getUsedHeapSize(): Long {
            return getHeapSize() - Runtime.getRuntime().freeMemory()
        }

        private fun getOpenGLVersion(): String {
            val actManager: ActivityManager =
                activity?.getSystemService(Context.ACTIVITY_SERVICE)!! as ActivityManager
            val configurationInfo = actManager.deviceConfigurationInfo
            return configurationInfo.glEsVersion
        }

    }
}
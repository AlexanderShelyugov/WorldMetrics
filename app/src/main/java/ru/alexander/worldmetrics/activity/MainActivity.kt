package ru.alexander.worldmetrics.activity

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapters.DemocracyIndexOverviewViewAdapter
import ru.alexander.worldmetrics.model.ExtractorsFactory.Companion.getDemocracyIndexExtractor


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val indexValuesView: RecyclerView = findViewById(R.id.rv_world_metrics_overview)
        val data = getDemocracyIndexExtractor().getLastYearData()
        val layoutManager = LinearLayoutManager(this)
        val adapter = DemocracyIndexOverviewViewAdapter(data)
        indexValuesView.adapter = adapter
        indexValuesView.layoutManager = layoutManager

        val debugTextView: TextView = findViewById(R.id.tv_debug_information)
        debugTextView.text = "RAM size: ${getRamSize()}\n" +
                "Heap size: ${getHeapSize()}\n" +
                "Currently used: ${getUsedHeapSize()} (${getUsedHeapSize().toFloat() * 100F / getHeapSize()}%)\n" +
                "OpenGL version: ${getOpenGLVersion()}"
    }

    private fun getRamSize(): Long {
        val actManager: ActivityManager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
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
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = actManager.deviceConfigurationInfo
        return configurationInfo.glEsVersion
    }
}
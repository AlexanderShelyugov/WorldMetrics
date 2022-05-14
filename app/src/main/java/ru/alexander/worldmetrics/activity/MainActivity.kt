package ru.alexander.worldmetrics.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.views.opengl.RedrawCountHelper

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RedrawCountHelper.activity = this
    }
}
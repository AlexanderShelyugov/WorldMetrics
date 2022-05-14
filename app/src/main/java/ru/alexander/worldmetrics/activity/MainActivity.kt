package ru.alexander.worldmetrics.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.RandomDataProvider
import ru.alexander.worldmetrics.opengl.RedrawCountHelper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var someDataProvider: RandomDataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RedrawCountHelper.activity = this
        Toast.makeText(this, someDataProvider.provideSomeData(), Toast.LENGTH_LONG).show()
    }
}
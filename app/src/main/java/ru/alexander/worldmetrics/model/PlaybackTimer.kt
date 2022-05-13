package ru.alexander.worldmetrics.model

import java.util.*

class PlaybackTimer {
    private var prevTime: Date? = null

    fun check(): Float {
        if (prevTime == null) {
            prevTime = Date()
            return 0f
        }

        val diff = Date().time - prevTime!!.time
        return diff.toFloat() / 1000
    }
}
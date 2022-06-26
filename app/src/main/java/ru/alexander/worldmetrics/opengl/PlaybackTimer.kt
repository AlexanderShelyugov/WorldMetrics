package ru.alexander.worldmetrics.opengl

import java.lang.System.currentTimeMillis
import java.util.concurrent.TimeUnit.MINUTES
import kotlin.random.Random

class PlaybackTimer {
    private var prevTime: Long? = null

    fun randomizeTiming() {
        val now = currentTimeMillis()
        prevTime = now - Random(now).nextLong(RANDOM_SPAN)
    }

    fun check(): Float {
        if (prevTime == null) {
            prevTime = currentTimeMillis()
            return 0f
        }

        val diff = currentTimeMillis() - prevTime!!
        return diff.toFloat() / 1000
    }

    private companion object {
        val RANDOM_SPAN = MINUTES.toMillis(1)
    }
}
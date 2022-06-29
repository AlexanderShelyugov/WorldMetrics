package ru.socialeducationapps.worldmetrics.opengl

import java.lang.System.currentTimeMillis
import java.util.concurrent.TimeUnit.MINUTES
import kotlin.random.Random

class PlaybackTimer {
    private var prevTime: Long? = null
    private var frame = 0

    fun randomizeTiming() {
        val now = currentTimeMillis()
        prevTime = now - Random(now).nextLong(RANDOM_SPAN)
    }

    fun currentFrame() = frame

    fun check(): Float {
        frame++
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
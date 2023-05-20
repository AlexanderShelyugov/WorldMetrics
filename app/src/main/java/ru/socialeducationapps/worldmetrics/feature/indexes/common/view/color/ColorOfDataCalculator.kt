package ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color

import android.graphics.Color
import java.lang.Float.max
import kotlin.math.min

class ColorOfDataCalculator(
    private val minValue: Float,
    private val maxValue: Float,
    medianValue: Float,
) {
    private var minColor: ColorType = 0
    private var maxColor: ColorType = 0
    private val median: Float

    init {
        median = rate(minValue, medianValue, maxValue)
    }

    fun setColorRange(minColor: ColorType, maxColor: ColorType) {
        this.minColor = minColor
        this.maxColor = maxColor
    }

    fun getColorFor(value: Float): ColorType {
        val rate = if (value.isNaN()) {
            0f
        } else {
            // Clip to bounds. Current can be out of [min; max].
            val v = min(max(value, minValue), maxValue)
            rate(minValue, v, maxValue)
        }
        return Color.rgb(
            mixForComponent(Color::red, rate),
            mixForComponent(Color::green, rate),
            mixForComponent(Color::blue, rate),
        )
    }

    private fun rate(min: Float, current: Float, max: Float) =
        (current - min) / (max - min)

    private fun mixForComponent(
        componentFunction: (ColorType) -> ColorComponentType,
        rate: Float,
    ): ColorComponentType {
        require(rate in 0f..1f)
        /* 0 - median - 1 */
        val minInclusion: Float /* 1 - 1 - 0 */
        val maxInclusion: Float /* 0 - 1 - 1 */
        if (rate < median) {
            minInclusion = 1f
            /*
            max(0) = 0
            max(median) = 1
            */
            maxInclusion = rate / median
        } else {
            /*
            min(median) = 1
            min(1) = 0
            */
            minInclusion = (1 - rate) / (1 - median)
            maxInclusion = 1f
        }

        val component = componentFunction(maxColor).toFloat() * maxInclusion +
                componentFunction(minColor).toFloat() * minInclusion
        return min(component.toInt(), MAX_COMPONENT)
    }

    private companion object {
        const val MAX_COMPONENT = 255
    }
}
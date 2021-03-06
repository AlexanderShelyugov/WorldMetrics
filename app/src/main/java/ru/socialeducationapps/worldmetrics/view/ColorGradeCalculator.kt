package ru.socialeducationapps.worldmetrics.view

import android.graphics.Color
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import java.lang.Float.max
import kotlin.math.min

private typealias ColorType = Int
private typealias ColorComponentType = Int

class ColorGradeCalculator(
    colorRange: Pair<ColorType, ColorType>,
    private val median: Float = DEFAULT_MEDIAN,
    private val minColor: ColorType = colorRange.first,
    private val maxColor: ColorType = colorRange.second,
) {
    fun evalColor(range: FeatureRange, current: Float): ColorType = evalColor(
        range.first, range.second, current
    )

    fun evalColor(min: Float, max: Float, current: Float): ColorType {
        val rate = if (current.isNaN()) {
            min
        } else {
            // Clip to bounds. Current can be out of [min; max].
            val v = min(max(current, min), max)
            (v - min) / (max - min)
        }
        return Color.rgb(
            mixForComponent(Color::red, rate),
            mixForComponent(Color::green, rate),
            mixForComponent(Color::blue, rate),
        )
    }

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
        const val DEFAULT_MEDIAN = .5f
        const val MAX_COMPONENT = 255
    }
}
package ru.alexander.worldmetrics.view

import android.graphics.Color
import kotlin.math.min

private typealias ColorType = Int
private typealias ColorComponentType = Int

class ColorGradeCalculator(
    colorRange: Pair<ColorType, ColorType>,
    private val median: Float = .4f,
    private val minColor: ColorType = colorRange.first,
    private val maxColor: ColorType = colorRange.second,
) {
    fun evalColor(min: Float, max: Float, current: Float): ColorType {
        require(current in min..max) { "$current out of range from $min to $max" }
        val rate = (current - min) / (max - min)
        return Color.rgb(
            mixForComponent(Color::red, rate),
            mixForComponent(Color::green, rate),
            mixForComponent(Color::blue, rate),
        )
    }

    private fun mixForComponent(
        component: (ColorType) -> ColorComponentType,
        rate: Float,
    ): ColorComponentType {
        require(rate in 0f..1f)
        /* 0 - median - 1 */
        val minInclusion: Float /* 1 - 1 - 0 */
        val maxInclusion: Float /* 0 - 1 - 1 */
        if (rate < median) {
            minInclusion = 1f
            maxInclusion = rate / median
        } else {
            /*
            min(median) = 1
            min(1) = 0
            */
            minInclusion = (1 - rate) / (1 - median)
            maxInclusion = 1f
        }

        val component = component(maxColor).toFloat() * maxInclusion +
                component(minColor).toFloat() * minInclusion
        return min(component.toInt(), 255)
    }
}
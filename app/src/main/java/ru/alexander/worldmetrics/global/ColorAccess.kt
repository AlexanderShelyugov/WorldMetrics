package ru.alexander.worldmetrics.global

import androidx.core.content.ContextCompat
import ru.alexander.worldmetrics.R

class ColorAccess private constructor() {
    companion object {
        val VALUE_DEFAULT_COLOR_RANGE =
            getColor(R.color.value_range_min) to getColor(R.color.value_range_max)

        fun getColor(colorId: Int) = ContextCompat.getColor(ContextAccess.context, colorId)
    }
}
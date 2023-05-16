package ru.socialeducationapps.worldmetrics.feature.helper.utils

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.utils.ContextAccess.Companion.context
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.ColorGradeCalculator

class ColorAccess private constructor() {
    companion object {
        val VALUE_DEFAULT_COLOR_RANGE =
            getColor(R.color.value_range_min) to getColor(R.color.value_range_max)
        val DEFAULT_COLOR_CALCULATOR = ColorGradeCalculator(VALUE_DEFAULT_COLOR_RANGE)

        fun getColor(colorId: Int) = ContextCompat.getColor(context, colorId)

        fun setColorFilter(drawable: Drawable, color: Int) {
            drawable.setTint(color)
        }
    }
}
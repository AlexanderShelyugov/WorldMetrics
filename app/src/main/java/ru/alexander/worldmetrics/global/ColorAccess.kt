package ru.alexander.worldmetrics.global

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.ContextCompat
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.ContextAccess.Companion.context
import ru.alexander.worldmetrics.view.ColorGradeCalculator

class ColorAccess private constructor() {
    companion object {
        val VALUE_DEFAULT_COLOR_RANGE =
            getColor(R.color.value_range_min) to getColor(R.color.value_range_max)
        val DEFAULT_COLOR_CALCULATOR = ColorGradeCalculator(VALUE_DEFAULT_COLOR_RANGE)

        fun getColor(colorId: Int) = ContextCompat.getColor(context, colorId)

        fun setColorFilter(drawable: Drawable, color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
            } else {
                drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            }
        }
    }
}
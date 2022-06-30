package ru.socialeducationapps.worldmetrics.global

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter.DecompositionType.SUB
import android.os.Build
import androidx.core.content.ContextCompat
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.global.ContextAccess.Companion.context
import ru.socialeducationapps.worldmetrics.view.ColorGradeCalculator

class ColorAccess private constructor() {
    companion object {
        val VALUE_DEFAULT_COLOR_RANGE =
            getColor(R.color.value_range_min) to getColor(R.color.value_range_max)
        val DEFAULT_COLOR_CALCULATOR = ColorGradeCalculator(VALUE_DEFAULT_COLOR_RANGE)

        fun getColor(colorId: Int) = ContextCompat.getColor(context, colorId)

        fun setColorFilter(drawable: Drawable, color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                drawable.colorFilter = BlendModeColorFilter(color, BlendMode.MULTIPLY)
            } else {
                ToastHelper.show("This is a backup color filter")
                drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
            }
        }
    }
}
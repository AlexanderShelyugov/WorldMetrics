package ru.socialeducationapps.worldmetrics.feature.helper.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import ru.socialeducationapps.worldmetrics.R

class ColorAccess private constructor() {
    companion object {
        fun getColor(ctx: Context, colorId: Int) = ContextCompat.getColor(ctx, colorId)

        fun getDefaultColorRange(ctx: Context) =
            getColor(ctx, R.color.value_range_min) to getColor(ctx, R.color.value_range_max)

        fun setColorFilter(drawable: Drawable, color: Int) {
            drawable.setTint(color)
        }
    }
}
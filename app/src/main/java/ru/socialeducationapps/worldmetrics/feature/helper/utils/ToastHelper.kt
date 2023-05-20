package ru.socialeducationapps.worldmetrics.feature.helper.utils

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText

class ToastHelper private constructor() {
    companion object {
        fun show(ctx: Context, message: String) {
            show(ctx, message, LENGTH_SHORT)
        }

        fun showLong(ctx: Context, message: String) {
            show(ctx, message, LENGTH_LONG)
        }

        private fun show(ctx: Context, message: String, duration: Int) {
            makeText(ctx, message, duration).show()
        }
    }
}
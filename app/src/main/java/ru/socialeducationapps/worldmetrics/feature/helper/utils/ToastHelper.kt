package ru.socialeducationapps.worldmetrics.feature.helper.utils

import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText

class ToastHelper private constructor() {
    companion object {
        fun show(message: String) {
            show(message, LENGTH_SHORT)
        }

        fun showLong(message: String) {
            show(message, LENGTH_LONG)
        }

        private fun show(message: String, duration: Int) {
            makeText(ContextAccess.context, message, duration).show()
        }
    }
}
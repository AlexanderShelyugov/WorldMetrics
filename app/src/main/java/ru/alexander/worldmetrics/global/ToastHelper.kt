package ru.alexander.worldmetrics.global

import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText

class ToastHelper {
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
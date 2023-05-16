package ru.socialeducationapps.worldmetrics.feature.helper.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class ContextAccess private constructor() {
    @Deprecated("We need to avoid context leakage. Architecture needs to be revisited")
    companion object {
        lateinit var contextSupplier: () -> Context
        val context get() = contextSupplier.invoke()
    }
}

fun Activity.hideKeyboard() {
    currentFocus?.let { view ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
package ru.socialeducationapps.worldmetrics.global

import android.content.Context

class ContextAccess private constructor() {
    companion object {
        lateinit var contextSupplier: () -> Context
        val context get() = contextSupplier.invoke()
    }
}
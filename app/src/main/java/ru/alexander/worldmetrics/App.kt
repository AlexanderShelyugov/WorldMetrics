package ru.alexander.worldmetrics

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import ru.alexander.worldmetrics.global.ContextAccess
import java.lang.ref.WeakReference

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ctx = WeakReference(this)
        ContextAccess.contextSupplier = App.Companion::getContext
    }

    override fun onTerminate() {
        super.onTerminate()
        ctx.clear()
    }

    companion object {
        private var ctx: WeakReference<Context> = WeakReference(null)

        fun getContext(): Context {
            return ctx.get()!!
        }
    }
}
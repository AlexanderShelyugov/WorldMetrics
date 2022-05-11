package ru.alexander.worldmetrics.global

import android.annotation.SuppressLint
import android.content.Context
import ru.alexander.worldmetrics.App.Companion.getContext
import java.io.InputStream

class AssetsContainer constructor(private val ctx: Context) {
    fun openAsset(filePath: String): InputStream {
        return ctx.assets.open(filePath)
    }

    fun openRawResource(id: Int): String {
        val input = ctx.resources.openRawResource(id)
        return inputStreamToString(input)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: AssetsContainer? = null

        fun getInstance(): AssetsContainer {
            if (instance == null) {
                instance = AssetsContainer(getContext())
            }
            return instance!!
        }

        fun openAsset(filePath: String): InputStream {
            return getInstance().openAsset(filePath)
        }

        fun openRawResource(id: Int): String {
            return getInstance().openRawResource(id)
        }

        private fun inputStreamToString(input: InputStream): String {
            val bytes = ByteArray(input.available())
            input.read(bytes)
            return String(bytes)
        }
    }
}
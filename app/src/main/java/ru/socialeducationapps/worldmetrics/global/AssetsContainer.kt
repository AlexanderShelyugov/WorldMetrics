package ru.socialeducationapps.worldmetrics.global

import ru.socialeducationapps.worldmetrics.global.ContextAccess.Companion.context
import java.io.InputStream

class AssetsContainer private constructor() {
    companion object {
        fun openAsset(filePath: String): InputStream {
            return context.assets.open(filePath)
        }

        fun openRawResource(id: Int): String {
            val input = context.resources.openRawResource(id)
            return inputStreamToString(input)
        }

        private fun inputStreamToString(input: InputStream): String {
            val bytes = ByteArray(input.available())
            input.read(bytes)
            return String(bytes)
        }
    }
}
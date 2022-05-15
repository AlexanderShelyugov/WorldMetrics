package ru.alexander.worldmetrics.model

class KeyValueList<V>(private val data: Map<String, V> = emptyMap()) {
    val keys = data.keys.toList().sorted()
    operator fun get(index: Int): V? = data[keys[index]]
    operator fun get(key: String): V? = data[key]

    val isEmpty get() = size == 0
    val size get() = data.size
}
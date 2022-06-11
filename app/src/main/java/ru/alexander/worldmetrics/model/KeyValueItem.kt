package ru.alexander.worldmetrics.model

data class KeyValueItem(
    var k: String,
    var v: String,
)

data class TripleItem(
    val first: String,
    var second: String,
    var third: String,
)
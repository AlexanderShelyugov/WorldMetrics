package ru.socialeducationapps.worldmetrics.modules.csv.model

import kotlin.Float.Companion.NaN

typealias CsvRow = List<String>

fun CsvRow.getInt(column: Int) = this[column].toInt()
fun CsvRow.getFloat(column: Int) = this[column].toFloatOrNull() ?: NaN
package ru.alexander.worldmetrics.modules.csv.service.api

interface CsvService {
    fun process(filePath: String, processor: (Sequence<List<String>>) -> Unit, separator: Char)

    fun process(filePath: String, processor: (Sequence<List<String>>) -> Unit)
}
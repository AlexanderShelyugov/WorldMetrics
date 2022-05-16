package ru.alexander.worldmetrics.service.api.csv

interface CsvService {
    fun process(filePath: String, processor: (Sequence<List<String>>) -> Unit)
}
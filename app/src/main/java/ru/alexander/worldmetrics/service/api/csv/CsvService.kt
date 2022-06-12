package ru.alexander.worldmetrics.service.api.csv

interface CsvService {
    fun process(
        filePath: String,
        processor: (Sequence<List<String>>) -> Unit,
        separator: Char = DEFAULT_DELIMITER
    )

    private companion object {
        private const val DEFAULT_DELIMITER: Char = ','
    }
}
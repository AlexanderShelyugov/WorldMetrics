package ru.socialeducationapps.worldmetrics.modules.csv.service.api

import ru.socialeducationapps.worldmetrics.modules.csv.model.CsvRow

interface CsvService {
    fun process(filePath: String, processor: (Sequence<CsvRow>) -> Unit, separator: Char)

    fun process(filePath: String, processor: (Sequence<CsvRow>) -> Unit)
}
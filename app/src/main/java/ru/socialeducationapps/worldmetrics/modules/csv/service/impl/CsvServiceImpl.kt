package ru.socialeducationapps.worldmetrics.modules.csv.service.impl

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import ru.socialeducationapps.worldmetrics.global.AssetsContainer
import ru.socialeducationapps.worldmetrics.modules.csv.model.CsvRow
import ru.socialeducationapps.worldmetrics.modules.csv.service.api.CsvService
import java.io.InputStream
import javax.inject.Inject

class CsvServiceImpl @Inject constructor() : CsvService {
    companion object {
        private const val DEFAULT_DELIMITER: Char = ','
    }

    override fun process(filePath: String, processor: (Sequence<CsvRow>) -> Unit) {
        process(filePath, processor, DEFAULT_DELIMITER)
    }

    override fun process(
        filePath: String,
        processor: (Sequence<CsvRow>) -> Unit,
        separator: Char,
    ) {
        val csv: InputStream = AssetsContainer.openAsset(filePath)
        csvReader {
            delimiter = separator
        }.open(csv) {
            val sequence = readAllAsSequence()
                .drop(1) // Skip header
            processor.invoke(sequence)
        }
    }
}
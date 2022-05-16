package ru.alexander.worldmetrics.service.impl.csv

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import ru.alexander.worldmetrics.global.AssetsContainer
import ru.alexander.worldmetrics.service.api.csv.CsvService
import java.io.InputStream
import javax.inject.Inject

class CsvServiceImpl @Inject constructor() : CsvService {
    companion object {
        private const val DEFAULT_DELIMITER: Char = ','
    }

    override fun process(filePath: String, processor: (Sequence<List<String>>) -> Unit) {
        process(filePath, processor, DEFAULT_DELIMITER)
    }

    override fun process(
        filePath: String,
        processor: (Sequence<List<String>>) -> Unit,
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
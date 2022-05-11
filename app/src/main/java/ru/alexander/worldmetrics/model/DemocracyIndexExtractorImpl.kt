package ru.alexander.worldmetrics.model

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import ru.alexander.worldmetrics.global.AssetsContainer.Companion.openAsset
import java.io.InputStream

internal class DemocracyIndexExtractorImpl constructor(private val fileName: String) :
    DemocracyIndexExtractor {
    private companion object {
        const val MAX_YEAR = 2020
        const val COLUMN_COUNTRY_NAME = 1
        const val COLUMN_YEAR = 2
        const val COLUMN_INDEX_VALUE = 3
    }

    override fun getLastYearData(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val csv: InputStream = openAsset(fileName)
        csvReader().open(csv) {
            readAllAsSequence()
                .drop(1) // Skip header
                .filter { row: List<String> -> MAX_YEAR.equals(row[COLUMN_YEAR].toInt()) }
                .forEach { row: List<String> ->
                    val country = row[COLUMN_COUNTRY_NAME]
                    val indexValue = row[COLUMN_INDEX_VALUE]
                    result[country] = indexValue
                }
        }
        return result
    }
}
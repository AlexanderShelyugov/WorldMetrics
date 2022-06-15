package ru.alexander.worldmetrics.democracy_index.service.impl

import ru.alexander.worldmetrics.democracy_index.model.DemocracyIndexValue
import ru.alexander.worldmetrics.democracy_index.service.api.DemocracyIndexService
import ru.alexander.worldmetrics.service.api.csv.CsvService
import javax.inject.Inject

class DemocracyIndexServiceImpl @Inject constructor(
    private val csvService: CsvService
) : DemocracyIndexService {
    private companion object {
        const val MAX_YEAR = 2020
        const val COLUMN_COUNTRY_CODE = 0
        const val COLUMN_COUNTRY_NAME = 1
        const val COLUMN_YEAR = 2
        const val COLUMN_INDEX_VALUE = 3

        val VALUES_RANGE = 10.8f to 98.1f
    }

    lateinit var filePath: String

    override fun getLastYearData(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        csvService.process(filePath) { rows ->
            rows
                .filter { MAX_YEAR == it[COLUMN_YEAR].toInt() }
                .forEach { row: List<String> ->
                    val country = row[COLUMN_COUNTRY_CODE]
                    val indexValue = row[COLUMN_INDEX_VALUE]
                    result[country] = indexValue
                }
        }
        return result
    }

    override fun getLastYearData(country: String): DemocracyIndexValue {
        val rows = getDataForCountry(country)
        val row = rows.asSequence().let {
            it.filter { row -> MAX_YEAR.equals(row[COLUMN_YEAR].toInt()) }.firstOrNull()
                ?: it.filter { row -> (MAX_YEAR - 1).equals(row[COLUMN_YEAR].toInt()) }
                    .firstOrNull()
        }
        return rowToIndexValue(row!!)
    }

    override fun getAllYearData(): Map<String, List<DemocracyIndexValue>> {
        lateinit var result: Map<String, List<DemocracyIndexValue>>
        csvService.process(filePath) { rows ->
            result = rows.asSequence()
                .map { rowToIndexValue(it) }
                .groupBy { it.country }
                .toMap()
        }
        return result
    }

    /**
     * Возвращает весь набор данных для отдельной страны
     */
    override fun getAllYearData(country: String): List<DemocracyIndexValue> =
        getDataForCountry(country).asSequence()
            .map { rowToIndexValue(it) }
            .toList()

    override fun getValueRange(): Pair<Float, Float> = VALUES_RANGE

    private fun rowToIndexValue(row: List<String>): DemocracyIndexValue = DemocracyIndexValue(
        row[0],
        row[1],
        row[2].toInt(),
        row[3].toFloat(),
        row[4].toFloat(),
        row[5].toFloat(),
        row[6].toFloat(),
        row[7].toFloat(),
        row[8].toFloat(),
    )

    private fun getDataForCountry(countryCode: String): List<List<String>> {
        lateinit var result: List<List<String>>
        csvService.process(filePath) { rows ->
            rows
                .filter { countryCode == it[COLUMN_COUNTRY_CODE] }
                .toList()
                .also { result = it }
        }
        return result
    }
}
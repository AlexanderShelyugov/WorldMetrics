package ru.alexander.worldmetrics.modules.democracy_index.service.impl

import ru.alexander.worldmetrics.modules.csv.service.api.CsvService
import ru.alexander.worldmetrics.modules.democracy_index.model.DemocracyIndexValue
import ru.alexander.worldmetrics.modules.democracy_index.service.api.DemocracyIndexService
import javax.inject.Inject

class DemocracyIndexServiceImpl @Inject constructor(
    private val csvService: CsvService
) : DemocracyIndexService {
    private companion object {
        const val MAX_YEAR = 2020
        const val COLUMN_COUNTRY_CODE = 0
        const val COLUMN_YEAR = 1
        const val COLUMN_INDEX_VALUE = 2
        const val COLUMN_ELECTORAL_PROCESS_AND_PLURALISM = 3
        const val COLUMN_FUNCTIONING_OF_GOVERNMENT = 4
        const val COLUMN_POLITICAL_PARTICIPATION = 5
        const val COLUMN_POLITICAL_CULTURE = 6
        const val COLUMN_CIVIL_LIBERTIES = 7

        val RANGE_VALUES = 10.8f to 98.1f
        val RANGE_ELECTORAL_PROCESS_AND_PLURALISM = 0.0f to 100.0f
        val RANGE_FUNCTIONING_OF_GOVERNMENT = 0.0f to 96.4f
        val RANGE_POLITICAL_PARTICIPATION = 5.6f to 100.0f
        val RANGE_POLITICAL_CULTURE = 12.5f to 100.0f
        val RANGE_CIVIL_LIBERTIES = 0.0f to 97.1f
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
            it.filter { row -> MAX_YEAR == row[COLUMN_YEAR].toInt() }.firstOrNull()
                ?: it.filter { row -> (MAX_YEAR - 1) == row[COLUMN_YEAR].toInt() }
                    .firstOrNull()
        }
        return rowToIndexValue(row!!)
    }

    override fun getAllYearData(): Map<String, List<DemocracyIndexValue>> {
        lateinit var result: Map<String, List<DemocracyIndexValue>>
        csvService.process(filePath) { rows ->
            result = rows.asSequence()
                .map { rowToIndexValue(it) }
                .groupBy { it.countryCode }
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

    override fun getValueRange() = RANGE_VALUES
    override fun getEPAPRange() = RANGE_ELECTORAL_PROCESS_AND_PLURALISM
    override fun getFOGRange() = RANGE_FUNCTIONING_OF_GOVERNMENT
    override fun getPPRange() = RANGE_POLITICAL_PARTICIPATION
    override fun getPCRange() = RANGE_POLITICAL_CULTURE
    override fun getCLRange() = RANGE_CIVIL_LIBERTIES

    private fun rowToIndexValue(row: List<String>): DemocracyIndexValue = DemocracyIndexValue(
        row[COLUMN_COUNTRY_CODE],
        row[COLUMN_YEAR].toInt(),
        row[COLUMN_INDEX_VALUE].toFloat(),
        row[COLUMN_ELECTORAL_PROCESS_AND_PLURALISM].toFloat(),
        row[COLUMN_FUNCTIONING_OF_GOVERNMENT].toFloat(),
        row[COLUMN_POLITICAL_PARTICIPATION].toFloat(),
        row[COLUMN_POLITICAL_CULTURE].toFloat(),
        row[COLUMN_CIVIL_LIBERTIES].toFloat(),
    )

    private fun getDataForCountry(countryCode: String): List<List<String>> {
        lateinit var result: List<List<String>>
        csvService.process(filePath) { rows ->
            rows
                .filter { it[COLUMN_COUNTRY_CODE].equals(countryCode, ignoreCase = true) }
                .toList()
                .also { result = it }
        }
        return result
    }
}
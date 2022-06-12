package ru.alexander.worldmetrics.service.impl

import ru.alexander.worldmetrics.model.PressFreedomValue
import ru.alexander.worldmetrics.service.api.PressFreedomService
import ru.alexander.worldmetrics.service.api.csv.CsvService
import javax.inject.Inject

class PressFreedomServiceImpl @Inject constructor(private val csvService: CsvService) :
    PressFreedomService {
    lateinit var filePath: String

    private companion object {
        const val MAX_YEAR = 2022
        const val COLUMN_COUNTRY_CODE = 0
        const val COLUMN_COUNTRY_NAME = 14
        const val COLUMN_INDEX_VALUE = 1
        const val COLUMN_POLITICAL_CONTEXT = 3
        const val COLUMN_ECONOMIC_CONTEXT = 5
        const val COLUMN_LEGAL_CONTEXT = 7
        const val COLUMN_SOCIAL_CONTEXT = 9
        const val COLUMN_SAFETY = 11
        const val COLUMN_YEAR = 19

        val VALUES_RANGE = 13.92f to 92.65f
    }

    override fun getLastYearData(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val processor: (Sequence<List<String>>) -> Unit = { rows ->
            rows
                .filter { MAX_YEAR == it[COLUMN_YEAR].toInt() }
                .map { it[COLUMN_COUNTRY_CODE] to it[COLUMN_INDEX_VALUE] }
                .associateTo(result) { it }
        }
        csvService.process(filePath, processor)
        return result
    }

    override fun getValueRange(): Pair<Float, Float> = VALUES_RANGE

    override fun getData(country: String): List<PressFreedomValue> {
        val result = mutableListOf<PressFreedomValue>()
        val processor: (Sequence<List<String>>) -> Unit = { rows ->
            rows
                .filter { it[COLUMN_COUNTRY_CODE] == country }
                .map { rowToIndexValue(it) }
                .toList()
                .run(result::addAll)
        }
        csvService.process(filePath, processor)
        return result
    }

    private fun rowToIndexValue(row: List<String>): PressFreedomValue = PressFreedomValue(
        row[COLUMN_COUNTRY_CODE],
        row[COLUMN_COUNTRY_NAME],
        row[COLUMN_INDEX_VALUE].toFloat(),
        row[COLUMN_POLITICAL_CONTEXT].toFloat(),
        row[COLUMN_ECONOMIC_CONTEXT].toFloat(),
        row[COLUMN_LEGAL_CONTEXT].toFloat(),
        row[COLUMN_SOCIAL_CONTEXT].toFloat(),
        row[COLUMN_SAFETY].toFloat(),
        row[COLUMN_YEAR].toInt(),
    )
}
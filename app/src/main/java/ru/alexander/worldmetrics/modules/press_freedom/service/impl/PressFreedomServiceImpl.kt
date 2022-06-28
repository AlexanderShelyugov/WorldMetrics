package ru.alexander.worldmetrics.modules.press_freedom.service.impl

import ru.alexander.worldmetrics.modules.csv.model.CsvRow
import ru.alexander.worldmetrics.modules.csv.service.api.CsvService
import ru.alexander.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.alexander.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject
import kotlin.Float.Companion.NaN

class PressFreedomServiceImpl @Inject constructor(private val csvService: CsvService) :
    PressFreedomService {
    lateinit var filePath: String

    private companion object {
        const val MAX_YEAR = 2022

        const val COLUMN_COUNTRY_CODE = 0
        const val COLUMN_INDEX_VALUE = 1
        const val COLUMN_POLITICAL_CONTEXT = 2
        const val COLUMN_ECONOMIC_CONTEXT = 3
        const val COLUMN_LEGAL_CONTEXT = 4
        const val COLUMN_SOCIAL_CONTEXT = 5
        const val COLUMN_SAFETY = 6
        const val COLUMN_YEAR = 7

        val RANGE_VALUES = 13.92f to 92.65f
        val RANGE_POLITICAL_CONTEXT = 22.22f to 94.89f
        val RANGE_ECONOMIC_CONTEXT = 0.0f to 90.38f
        val RANGE_LEGAL_CONTEXT = 15.79f to 92.23f
        val RANGE_SOCIAL_CONTEXT = 12.0f to 95.0f
        val RANGE_SAFETY = 4.63f to 96.46f
    }

    override fun getLastYearData(): List<SimpleCountryValue> {
        lateinit var result: List<SimpleCountryValue>
        csvService.process(filePath) { rows ->
            rows.filter { MAX_YEAR == it[COLUMN_YEAR].toInt() }
                .map {
                    SimpleCountryValue(
                        it[COLUMN_COUNTRY_CODE],
                        it[COLUMN_INDEX_VALUE].toFloatOrNull() ?: NaN
                    )
                }
                .toList()
                .also { result = it }
        }
        return result
    }

    override fun getLastYearData(countryCode: String): PressFreedomValue {
        lateinit var result: PressFreedomValue
        csvService.process(filePath) { rows ->
            rows
                .filter { it[COLUMN_COUNTRY_CODE].equals(countryCode, ignoreCase = true) }
                .filter { MAX_YEAR == it[COLUMN_YEAR].toInt() }
                .first()
                .let { rowToIndexValue(it) }
                .also { result = it }
        }
        return result
    }

    override fun getAllData(countryCode: String): List<PressFreedomValue> {
        lateinit var result: List<PressFreedomValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { it[COLUMN_COUNTRY_CODE].equals(countryCode, ignoreCase = true) }
                .map { rowToIndexValue(it) }
                .toList()
                .also { result = it }
        }
        return result
    }

    override fun getValueRange() = RANGE_VALUES
    override fun getPCRange() = RANGE_POLITICAL_CONTEXT
    override fun getECRange() = RANGE_ECONOMIC_CONTEXT
    override fun getLCRange() = RANGE_LEGAL_CONTEXT
    override fun getSCRange() = RANGE_SOCIAL_CONTEXT
    override fun getSRange() = RANGE_SAFETY

    private fun rowToIndexValue(row: CsvRow): PressFreedomValue = PressFreedomValue(
        row[COLUMN_COUNTRY_CODE],
        row[COLUMN_INDEX_VALUE].toFloat(),
        row[COLUMN_POLITICAL_CONTEXT].toFloat(),
        row[COLUMN_ECONOMIC_CONTEXT].toFloat(),
        row[COLUMN_LEGAL_CONTEXT].toFloat(),
        row[COLUMN_SOCIAL_CONTEXT].toFloat(),
        row[COLUMN_SAFETY].toFloat(),
        row[COLUMN_YEAR].toInt(),
    )
}
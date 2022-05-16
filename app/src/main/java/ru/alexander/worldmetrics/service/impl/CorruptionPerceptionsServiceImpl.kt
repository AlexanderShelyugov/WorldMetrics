package ru.alexander.worldmetrics.service.impl

import ru.alexander.worldmetrics.model.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.service.api.CorruptionPerceptionsService
import ru.alexander.worldmetrics.service.api.csv.CsvService
import javax.inject.Inject
import kotlin.Float.Companion.NaN

class CorruptionPerceptionsServiceImpl @Inject constructor(
    private val csvService: CsvService
) : CorruptionPerceptionsService {
    private companion object {
        const val MAX_YEAR = 2015
        const val COLUMN_COUNTRY_NAME = 0
        const val COLUMN_INDEX_VALUE = 17
    }

    lateinit var filePath: String

    override fun getLastYearData(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        csvService.process(filePath) { rows ->
            rows
                .map { it[COLUMN_COUNTRY_NAME] to it[COLUMN_INDEX_VALUE] }
                .associateTo(result) { it }
        }
        return result
    }

    override fun getLastYearData(country: String): CorruptionPerceptionsValue {
        lateinit var result: CorruptionPerceptionsValue
        csvService.process(filePath) { rows ->
            rows
                .filter { country == it[COLUMN_COUNTRY_NAME] }
                .map(this::rowToIndexValue)
                .first()
                .also { result = it }
        }
        return result
    }

    private fun rowToIndexValue(row: List<String>): CorruptionPerceptionsValue =
        CorruptionPerceptionsValue(
            row[COLUMN_COUNTRY_NAME],
            MAX_YEAR,
            row[COLUMN_INDEX_VALUE].toFloatOrNull() ?: NaN,
        )
}
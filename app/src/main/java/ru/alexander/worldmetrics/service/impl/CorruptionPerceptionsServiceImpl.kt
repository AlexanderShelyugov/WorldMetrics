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
        const val COLUMN_COUNTRY_CODE = 0

        val COLUMN_MIN_YEAR = 2 to 1998
        val COLUMN_MAX_YEAR = 19 to 2015
    }

    lateinit var filePath: String

    override fun getLastYearData(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        csvService.process(filePath) { rows ->
            rows
                .map { it[COLUMN_COUNTRY_CODE] to it[COLUMN_MAX_YEAR.first] }
                .associateTo(result) { it }
        }
        return result
    }

    override fun getLastYearData(country: String): CorruptionPerceptionsValue {
        lateinit var result: CorruptionPerceptionsValue
        csvService.process(filePath) { rows ->
            rows
                .filter { country == it[COLUMN_COUNTRY_CODE] }
                .map(this::rowToIndexValue)
                .first()
                .also { result = it }
        }
        return result
    }

    override fun getAllData(country: String): List<CorruptionPerceptionsValue> {
        lateinit var result: List<CorruptionPerceptionsValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { country == it[COLUMN_COUNTRY_CODE] }
                .flatMap {
                    (COLUMN_MIN_YEAR.first until it.size).map { i ->
                        val year = COLUMN_MIN_YEAR.second + (i - 1)
                        val value = it[i]
                        Triple(country, year, value)
                    }.asSequence()
                }
                .map(this::dataToIndexValue)
                .toList()
                .also { result = it }
        }
        return result
    }

    private fun rowToIndexValue(row: List<String>): CorruptionPerceptionsValue =
        CorruptionPerceptionsValue(
            row[COLUMN_COUNTRY_CODE],
            COLUMN_MAX_YEAR.second,
            row[COLUMN_MAX_YEAR.first].toFloatOrNull() ?: NaN,
        )

    private fun dataToIndexValue(row: Triple<String, Int, String>): CorruptionPerceptionsValue =
        CorruptionPerceptionsValue(row.first, row.second, row.third.toFloatOrNull() ?: NaN)

}
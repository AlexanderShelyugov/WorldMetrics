package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.impl

import ru.socialeducationapps.worldmetrics.feature.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.SimpleCountryValue
import javax.inject.Inject
import kotlin.Float.Companion.NaN

class CorruptionPerceptionsCsvService @Inject constructor(
    private val csvService: CsvService,
) : CorruptionPerceptionsService {
    private companion object {
        const val COLUMN_COUNTRY_CODE = 0

        val COLUMN_MIN_YEAR = 2 to 1998
        val COLUMN_MAX_YEAR = 19 to 2015
        val VALUES_RANGE = 8f to 91f
    }

    lateinit var filePath: String

    override suspend fun getLastYearData(): List<SimpleCountryValue> {
        lateinit var result: List<SimpleCountryValue>
        csvService.process(filePath) { rows ->
            rows
                .map {
                    SimpleCountryValue(
                        it[COLUMN_COUNTRY_CODE].lowercase(),
                        it[COLUMN_MAX_YEAR.first].toFloatOrNull() ?: NaN
                    )
                }
                .toList()
                .also { result = it }
        }
        return result
    }

    override suspend fun getLastYearData(countryCode: String): CorruptionPerceptionsValue {
        lateinit var item: CorruptionPerceptionsValue
        csvService.process(filePath) { rows ->
            rows
                .filter { it[COLUMN_COUNTRY_CODE].equals(countryCode, ignoreCase = true) }
                .map {
                    Triple(
                        countryCode.lowercase(),
                        COLUMN_MAX_YEAR.second,
                        it[COLUMN_MAX_YEAR.first]
                    )
                }
                .map(this::dataToIndexValue)
                .first()
                .also { item = it }
        }
        return item
    }

    override suspend fun getAllData(countryCode: String): List<CorruptionPerceptionsValue> {
        lateinit var result: List<CorruptionPerceptionsValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { it[COLUMN_COUNTRY_CODE].equals(countryCode, ignoreCase = true) }
                .flatMap {
                    (COLUMN_MIN_YEAR.first until it.size).map { i ->
                        val year = COLUMN_MIN_YEAR.second + (i - 1)
                        val value = it[i]
                        Triple(countryCode, year, value)
                    }.asSequence()
                }
                .map(this::dataToIndexValue)
                .toList()
                .also { result = it }
        }
        return result
    }

    override suspend fun getValueRange() = VALUES_RANGE

    private fun dataToIndexValue(row: Triple<String, Int, String>): CorruptionPerceptionsValue =
        CorruptionPerceptionsValue(row.first, row.second, row.third.toFloatOrNull() ?: NaN)
}
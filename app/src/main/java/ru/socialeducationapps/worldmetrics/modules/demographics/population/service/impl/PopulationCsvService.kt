package ru.socialeducationapps.worldmetrics.modules.demographics.population.service.impl

import ru.socialeducationapps.worldmetrics.model.CountriesData
import ru.socialeducationapps.worldmetrics.modules.csv.model.CsvRow
import ru.socialeducationapps.worldmetrics.modules.csv.model.getFloat
import ru.socialeducationapps.worldmetrics.modules.csv.model.getInt
import ru.socialeducationapps.worldmetrics.modules.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.modules.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import javax.inject.Inject

class PopulationCsvService @Inject constructor(
    private val csvService: CsvService,
) : PopulationService {
    lateinit var filePath: String

    override suspend fun getLastYearData(): List<SimpleCountryValue> {
        lateinit var result: List<SimpleCountryValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { CountriesData.getNameIdByCode(it[COLUMN_ISO_3_CODE]) != null }
                .groupBy { it[COLUMN_ISO_3_CODE] }
                .values.asSequence()
                .map { rowsPerCountry ->
                    rowsPerCountry.maxByOrNull {
                        it[COLUMN_YEAR].toIntOrNull() ?: -1
                    }!!
                }
                .map {
                    SimpleCountryValue(
                        it[COLUMN_ISO_3_CODE].lowercase(),
                        it.getFloat(COLUMN_POPULATION_TOTAL)
                    )
                }
                .toList()
                .also { result = it }
        }
        return result
    }

    override suspend fun getLastYearData(countryCode: String): PopulationIndexValue {
        lateinit var item: PopulationIndexValue
        csvService.process(filePath) { rows ->
            rows
                .filter { CountriesData.getNameIdByCode(it[COLUMN_ISO_3_CODE]) != null }
                .filter { it[COLUMN_ISO_3_CODE].equals(countryCode, ignoreCase = true) }
                .map(this::rowToIndexValue)
                .maxByOrNull { it.year }!!
                .also { item = it }
        }
        return item
    }

    override suspend fun getAllData(countryCode: String): List<PopulationIndexValue> {
        lateinit var item: List<PopulationIndexValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { CountriesData.getNameIdByCode(it[COLUMN_ISO_3_CODE]) != null }
                .filter { it[COLUMN_ISO_3_CODE].equals(countryCode, ignoreCase = true) }
                .map(this::rowToIndexValue)
                .toList()
                .also { item = it }
        }
        return item
    }

    override suspend fun getTotalPopulationRange() = RANGE_TOTAL
    override suspend fun getMalePopulationRange() = RANGE_TOTAL
    override suspend fun getFemalePopulationRange() = RANGE_TOTAL
    override suspend fun getPopulationDensityRange() = RANGE_TOTAL

    private fun rowToIndexValue(row: CsvRow): PopulationIndexValue = PopulationIndexValue(
        row[COLUMN_ISO_3_CODE],
        row.getInt(COLUMN_YEAR),
        row.getFloat(COLUMN_POPULATION_TOTAL),
        row.getFloat(COLUMN_POPULATION_MALE),
        row.getFloat(COLUMN_POPULATION_FEMALE),
        row.getFloat(COLUMN_POPULATION_DENSITY),
    )

    private companion object {
        const val COLUMN_ISO_3_CODE = 0
        const val COLUMN_YEAR = 1
        const val COLUMN_POPULATION_TOTAL = 4
        const val COLUMN_POPULATION_MALE = 2
        const val COLUMN_POPULATION_FEMALE = 3
        const val COLUMN_POPULATION_DENSITY = 5

        val RANGE_TOTAL = 55.032f to 1448471.4f
    }
}
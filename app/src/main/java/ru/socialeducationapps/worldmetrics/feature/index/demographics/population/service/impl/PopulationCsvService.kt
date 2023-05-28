package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.impl

import ru.socialeducationapps.worldmetrics.feature.csv.model.CsvRow
import ru.socialeducationapps.worldmetrics.feature.csv.model.getFloat
import ru.socialeducationapps.worldmetrics.feature.csv.model.getInt
import ru.socialeducationapps.worldmetrics.feature.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CountryFeatureValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.toFeatureMedianRange
import javax.inject.Inject

class PopulationCsvService @Inject constructor(
    private val csvService: CsvService,
) : PopulationService {
    lateinit var filePath: String

    override suspend fun getLastYearData(): List<CountryFeatureValue> {
        lateinit var result: List<CountryFeatureValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { CountryResourceBindings.getNameIdByCode(it[COLUMN_ISO_3_CODE]) != null }
                .groupBy { it[COLUMN_ISO_3_CODE] }
                .values.asSequence()
                .map { rowsPerCountry ->
                    rowsPerCountry.maxByOrNull {
                        it[COLUMN_YEAR].toIntOrNull() ?: -1
                    }!!
                }
                .map {
                    CountryFeatureValue(
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
                .filter { CountryResourceBindings.getNameIdByCode(it[COLUMN_ISO_3_CODE]) != null }
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
                .filter { CountryResourceBindings.getNameIdByCode(it[COLUMN_ISO_3_CODE]) != null }
                .filter { it[COLUMN_ISO_3_CODE].equals(countryCode, ignoreCase = true) }
                .map(this::rowToIndexValue)
                .toList()
                .also { item = it }
        }
        return item
    }

    override suspend fun getTotalPopulationRange() = RANGE_TOTAL
    override suspend fun getMalePopulationRange() = RANGE_MALE
    override suspend fun getFemalePopulationRange() = RANGE_FEMALE
    override suspend fun getPopulationDensityRange() = RANGE_DENSITY
    override suspend fun getMinMedianMaxForAllCountries() = Triple(
        RANGE_TOTAL.first,
        (RANGE_TOTAL.first + RANGE_TOTAL.second) / 2f,
        RANGE_TOTAL.second,
    )

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

        val RANGE_TOTAL = (55.032f to 1448471.4f).toFeatureMedianRange()
        val RANGE_FEMALE = (48.511f to 706016.75f).toFeatureMedianRange()
        val RANGE_MALE = (49.333f to 742454.7f).toFeatureMedianRange()
        val RANGE_DENSITY = (0.139f to 26696.645f).toFeatureMedianRange()
    }
}
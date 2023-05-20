package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.impl

import ru.socialeducationapps.worldmetrics.feature.csv.model.CsvRow
import ru.socialeducationapps.worldmetrics.feature.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CountryFeatureValue
import javax.inject.Inject
import kotlin.Float.Companion.NaN

class GDPCsvService @Inject constructor(
    private val csvService: CsvService,
) : GDPService {
    lateinit var filePath: String

    override suspend fun getLastYearData(): List<CountryFeatureValue> {
        lateinit var result: List<CountryFeatureValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { getNameIdByCode(it[COLUMN_COUNTRY_CODE]) != null }
                .groupBy { it[COLUMN_COUNTRY_CODE] }
                .values.asSequence()
                .map { rowsPerCountry ->
                    rowsPerCountry.maxByOrNull {
                        it[COLUMN_YEAR].toIntOrNull() ?: -1
                    }!!
                }
                .map {
                    CountryFeatureValue(
                        it[COLUMN_COUNTRY_CODE].lowercase(),
                        it[COLUMN_VALUE_USD_CAP].toFloatOrNull() ?: NaN
                    )
                }
                .toList()
                .also { result = it }
        }
        return result
    }

    override suspend fun getLastYearData(countryCode: String): GDPValue {
        lateinit var item: GDPValue
        csvService.process(filePath) { rows ->
            rows
                .filter { getNameIdByCode(it[COLUMN_COUNTRY_CODE]) != null }
                .filter { it[COLUMN_COUNTRY_CODE].equals(countryCode, ignoreCase = true) }
                .map(this::rowToIndexValue)
                .maxByOrNull { it.year }!!
                .also { item = it }
        }
        return item
    }

    override suspend fun getAllData(countryCode: String): List<GDPValue> {
        lateinit var item: List<GDPValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { getNameIdByCode(it[COLUMN_COUNTRY_CODE]) != null }
                .filter { it[COLUMN_COUNTRY_CODE].equals(countryCode, ignoreCase = true) }
                .map(this::rowToIndexValue)
                .toList()
                .also { item = it }
        }
        return item
    }

    override fun getValueMlnUsdRange() = VALUE_MLN_USD_RANGE
    override fun getValueUsdPerCapitaRange() = VALUE_USD_PER_CAPITA

    private fun rowToIndexValue(row: CsvRow): GDPValue = GDPValue(
        row[COLUMN_COUNTRY_CODE],
        row[COLUMN_YEAR].toInt(),
        row[COLUMN_VALUE_MLN_USD].toFloat(),
        row[COLUMN_VALUE_USD_CAP].toFloatOrNull() ?: NaN,
    )

    private companion object {
        val VALUE_MLN_USD_RANGE = 21461.473f to 24274126f
        val VALUE_USD_PER_CAPITA = 1560.0228f to 134340.38f
        const val COLUMN_COUNTRY_CODE = 0
        const val COLUMN_YEAR = 1
        const val COLUMN_VALUE_MLN_USD = 2
        const val COLUMN_VALUE_USD_CAP = 3
    }
}

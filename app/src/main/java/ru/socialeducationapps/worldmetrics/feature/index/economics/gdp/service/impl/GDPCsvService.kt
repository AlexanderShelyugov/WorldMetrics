package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.impl

import ru.socialeducationapps.worldmetrics.feature.csv.model.CsvRow
import ru.socialeducationapps.worldmetrics.feature.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CountryFeatureValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.math.statistics.MedianCalculator.Companion.calculateMedian
import javax.inject.Inject
import kotlin.Float.Companion.NaN

class GDPCsvService @Inject constructor(
    private val csvService: CsvService,
) : GDPService {
    lateinit var filePath: String

    private var rangeMlnUsd: FeatureMedianRange? = null
    private var rangeUsdPerCapita: FeatureMedianRange? = null

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

    private fun getAllData(): List<GDPValue> {
        lateinit var item: List<GDPValue>
        csvService.process(filePath) { rows ->
            rows
                .filter { getNameIdByCode(it[COLUMN_COUNTRY_CODE]) != null }
                .map(this::rowToIndexValue)
                .toList()
                .also { item = it }
        }
        return item
    }

    override fun getValueMlnUsdRange(): FeatureMedianRange {
        if (rangeMlnUsd == null) {
            rangeMlnUsd = computeValueMlnUsdRange(getAllData())
        }
        return rangeMlnUsd!!
    }

    override fun getValueUsdPerCapitaRange(): FeatureMedianRange {
        if (rangeUsdPerCapita == null) {
            rangeUsdPerCapita = computeUsdPerCapitaRange(getAllData())
        }
        return rangeUsdPerCapita!!
    }

    private fun computeValueMlnUsdRange(allValues: List<GDPValue>): FeatureMedianRange {
        val data = allValues.map { it.valueMlnUsd }
            .filter { it.isFinite() }
        return FeatureMedianRange(
            data.min(),
            calculateMedian(data),
            data.max(),
        )
    }

    private fun computeUsdPerCapitaRange(allValues: List<GDPValue>): FeatureMedianRange {
        val data = allValues.map { it.valueUsdCap }
            .filter { it.isFinite() }
        return FeatureMedianRange(
            data.min(),
            calculateMedian(data),
            data.max(),
        )
    }

    override suspend fun getMinMedianMaxForAllCountries() =
        getValueUsdPerCapitaRange()

    private fun rowToIndexValue(row: CsvRow): GDPValue = GDPValue(
        row[COLUMN_COUNTRY_CODE],
        row[COLUMN_YEAR].toInt(),
        row[COLUMN_VALUE_MLN_USD].toFloat(),
        row[COLUMN_VALUE_USD_CAP].toFloatOrNull() ?: NaN,
    )

    private companion object {
        const val COLUMN_COUNTRY_CODE = 0
        const val COLUMN_YEAR = 1
        const val COLUMN_VALUE_MLN_USD = 2
        const val COLUMN_VALUE_USD_CAP = 3
    }
}

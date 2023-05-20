package ru.socialeducationapps.worldmetrics.feature.indexes.common.api

import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CountryFeatureValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange

interface IndexOverviewService {
    /**
     * Returns data for all countries for last year in a compressed way.
     */
    suspend fun getLastYearData(): List<CountryFeatureValue>

    /**
     * Returns min, median and max values for all countries on a given index
     */
    suspend fun getMinMedianMaxForAllCountries(): FeatureMedianRange
}
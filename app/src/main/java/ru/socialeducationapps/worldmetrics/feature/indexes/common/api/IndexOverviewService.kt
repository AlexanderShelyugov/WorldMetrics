package ru.socialeducationapps.worldmetrics.feature.indexes.common.api

import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.SimpleCountryValue

interface IndexOverviewService {
    /**
     * Returns data for all countries for last year in a compressed way.
     */
    suspend fun getLastYearData(): List<SimpleCountryValue>
}
package ru.socialeducationapps.worldmetrics.modules.indexes.service.api

import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

interface IndexOverviewService {
    /**
     * Returns data for all countries for last year in a compressed way.
     */
    suspend fun getLastYearData(): List<SimpleCountryValue>
}
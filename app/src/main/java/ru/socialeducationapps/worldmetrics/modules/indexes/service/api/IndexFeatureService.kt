package ru.socialeducationapps.worldmetrics.modules.indexes.service.api

interface IndexFeatureService<T> : IndexOverviewService {
    /**
     * Returns data for a given country for last year
     */
    suspend fun getLastYearData(countryCode: String): T

    /**
     * Returns all data for specific country
     * @param countryCode
     */
    suspend fun getAllData(countryCode: String): List<T>
}
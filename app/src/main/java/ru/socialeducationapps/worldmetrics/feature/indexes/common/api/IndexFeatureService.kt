package ru.socialeducationapps.worldmetrics.feature.indexes.common.api

interface IndexFeatureService<T> {
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
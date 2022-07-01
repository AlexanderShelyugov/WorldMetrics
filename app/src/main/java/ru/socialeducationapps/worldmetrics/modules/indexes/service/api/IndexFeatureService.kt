package ru.socialeducationapps.worldmetrics.modules.indexes.service.api

import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

interface IndexFeatureService<T> {
    /**
     * Returns data for all countries for last year in a compressed way.
     */
    suspend fun getLastYearData(): List<SimpleCountryValue>

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
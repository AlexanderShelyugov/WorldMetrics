package ru.alexander.worldmetrics.modules.corruption_perceptions.service.api

import ru.alexander.worldmetrics.model.indexes.FeatureRange
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue

interface CorruptionPerceptionsService {
    /**
     * Return all countries index value for last year
     */
    fun getLastYearData(): Map<String, String>

    /**
     * Returns data of last year for a given country
     */
    fun getLastYearData(countryCode: String): CorruptionPerceptionsValue

    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): FeatureRange

    /**
     * Returns all data for specific country
     * @param country
     */
    fun getAllData(countryCode: String): List<CorruptionPerceptionsValue>
}
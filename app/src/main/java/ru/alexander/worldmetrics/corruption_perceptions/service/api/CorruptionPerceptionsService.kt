package ru.alexander.worldmetrics.corruption_perceptions.service.api

import ru.alexander.worldmetrics.corruption_perceptions.model.CorruptionPerceptionsValue

interface CorruptionPerceptionsService {
    /**
     * Возвращает данные за последний год.
     */
    fun getLastYearData(): Map<String, String>

    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): Pair<Float, Float>

    /**
     * Returns all data for specific country
     * @param country
     */
    fun getAllData(countryCode: String): List<CorruptionPerceptionsValue>
}
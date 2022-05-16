package ru.alexander.worldmetrics.service.api

import ru.alexander.worldmetrics.model.CorruptionPerceptionsValue

interface CorruptionPerceptionsService {
    /**
     * Возвращает данные за последний год.
     */
    fun getLastYearData(): Map<String, String>

    /**
     * Возвращает данные за последний год для конкретной страны.
     *
     * @param country - страна
     */
    fun getLastYearData(country: String): CorruptionPerceptionsValue
}
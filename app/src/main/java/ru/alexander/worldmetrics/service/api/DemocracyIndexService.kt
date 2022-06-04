package ru.alexander.worldmetrics.service.api

import ru.alexander.worldmetrics.model.DemocracyIndexValue

interface DemocracyIndexService {
    /**
     * Возвращает данные за последний год.
     */
    fun getLastYearData(): Map<String, String>

    /**
     * Возвращает данные за последний год для конкретной страны.
     *
     * @param country - страна
     */
    fun getLastYearData(country: String): DemocracyIndexValue

    /**
     * Возвращает весь набор данных
     */
    fun getAllYearData(): Map<String, List<DemocracyIndexValue>>

    /**
     * Возвращает весь набор данных для отдельной страны
     */
    fun getAllYearData(country: String): List<DemocracyIndexValue>
}
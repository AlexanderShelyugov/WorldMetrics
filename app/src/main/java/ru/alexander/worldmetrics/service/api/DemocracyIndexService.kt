package ru.alexander.worldmetrics.service.api

import ru.alexander.worldmetrics.model.democracy_index.DemocracyIndexValue

interface DemocracyIndexService {
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
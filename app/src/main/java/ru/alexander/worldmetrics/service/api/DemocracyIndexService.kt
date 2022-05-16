package ru.alexander.worldmetrics.service.api

import ru.alexander.worldmetrics.model.DemocracyIndexValue

interface DemocracyIndexService {
    /**
     * Возвращает данные за последний год.
     */
    fun getLastYearData(): Map<String, String>

    fun getLastYearData(country: String): DemocracyIndexValue
}
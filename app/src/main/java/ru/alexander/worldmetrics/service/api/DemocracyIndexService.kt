package ru.alexander.worldmetrics.service.api

interface DemocracyIndexService {
    /**
     * Возвращает данные за последний год.
     */
    fun getLastYearData(): Map<String, String>
}
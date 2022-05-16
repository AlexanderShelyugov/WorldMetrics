package ru.alexander.worldmetrics.service.api

interface PressFreedomService {
    /**
     * Возвращает данные за последний год.
     */
    fun getLastYearData(): Map<String, String>
}
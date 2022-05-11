package ru.alexander.worldmetrics.model

interface DemocracyIndexExtractor {
    /**
     * Возвращает данные за последний год.
     */
    fun getLastYearData(): Map<String, String>
}
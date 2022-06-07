package ru.alexander.worldmetrics.service.api

import ru.alexander.worldmetrics.model.PressFreedomValue

interface PressFreedomService {
    /**
     * Returns data for the most recent year for all countries.
     */
    fun getLastYearData(): Map<String, String>

    /**
     * Returns data for specific country.
     *
     * @param country
     */
    fun getData(country: String): List<PressFreedomValue>
}
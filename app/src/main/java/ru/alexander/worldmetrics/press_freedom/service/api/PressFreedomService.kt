package ru.alexander.worldmetrics.press_freedom.service.api

import ru.alexander.worldmetrics.model.press_freedom.PressFreedomValue

interface PressFreedomService {
    /**
     * Returns data for the most recent year for all countries.
     */
    fun getLastYearData(): Map<String, String>

    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): Pair<Float, Float>

    /**
     * Returns data for specific country.
     *
     * @param country
     */
    fun getData(country: String): List<PressFreedomValue>
}
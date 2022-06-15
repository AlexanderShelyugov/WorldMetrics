package ru.alexander.worldmetrics.modules.press_freedom.service.api

import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomValue

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
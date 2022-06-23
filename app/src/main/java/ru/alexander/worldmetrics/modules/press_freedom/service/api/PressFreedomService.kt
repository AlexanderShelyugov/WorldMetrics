package ru.alexander.worldmetrics.modules.press_freedom.service.api

import ru.alexander.worldmetrics.model.indexes.FeatureRange
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomValue

interface PressFreedomService {
    /**
     * Returns data for the most recent year for all countries.
     */
    fun getLastYearData(): Map<String, String>

    /**
     * Returns data for the most recent year for all countries.
     */
    fun getLastYearData(countryCode: String): PressFreedomValue

    /**
     * Returns data for specific country.
     *
     * @param country
     */
    fun getData(country: String): List<PressFreedomValue>

    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): FeatureRange

    /**
     * Returns political context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getPCRange(): FeatureRange

    /**
     * Returns economic context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getECRange(): FeatureRange

    /**
     * Returns legal context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getLCRange(): FeatureRange

    /**
     * Returns social context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getSCRange(): FeatureRange

    /**
     * Returns safety range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getSRange(): FeatureRange
}
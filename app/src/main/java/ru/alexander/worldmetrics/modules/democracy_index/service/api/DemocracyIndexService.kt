package ru.alexander.worldmetrics.modules.democracy_index.service.api

import ru.alexander.worldmetrics.model.indexes.FeatureRange
import ru.alexander.worldmetrics.modules.democracy_index.model.DemocracyIndexValue

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

    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): FeatureRange

    /**
     * Returns value of electoral process and pluralism for last year across countries
     *
     * @return min and max of electoral process and pluralism
     */
    fun getEPAPRange(): FeatureRange

    /**
     * Returns value of functioning of government for last year across countries
     *
     * @return min and max of functioning of government
     */
    fun getFOGRange(): FeatureRange

    /**
     * Returns value of political participation for last year across countries
     *
     * @return min and max of political participation
     */
    fun getPPRange(): FeatureRange

    /**
     * Returns value of political culture for last year across countries
     *
     * @return min and max of political culture
     */
    fun getPCRange(): FeatureRange

    /**
     * Returns value of civil liberties for last year across countries
     *
     * @return min and max of civil liberties
     */
    fun getCLRange(): FeatureRange
}
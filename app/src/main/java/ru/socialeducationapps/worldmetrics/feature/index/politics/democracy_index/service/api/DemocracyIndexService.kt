package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api

import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexOverviewService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange

interface DemocracyIndexService : IndexFeatureService<DemocracyIndexValue>,
    IndexOverviewService {
    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): FeatureMedianRange

    /**
     * Returns value of electoral process and pluralism for last year across countries
     *
     * @return min and max of electoral process and pluralism
     */
    fun getEPAPRange(): FeatureMedianRange

    /**
     * Returns value of functioning of government for last year across countries
     *
     * @return min and max of functioning of government
     */
    fun getFOGRange(): FeatureMedianRange

    /**
     * Returns value of political participation for last year across countries
     *
     * @return min and max of political participation
     */
    fun getPPRange(): FeatureMedianRange

    /**
     * Returns value of political culture for last year across countries
     *
     * @return min and max of political culture
     */
    fun getPCRange(): FeatureMedianRange

    /**
     * Returns value of civil liberties for last year across countries
     *
     * @return min and max of civil liberties
     */
    fun getCLRange(): FeatureMedianRange
}
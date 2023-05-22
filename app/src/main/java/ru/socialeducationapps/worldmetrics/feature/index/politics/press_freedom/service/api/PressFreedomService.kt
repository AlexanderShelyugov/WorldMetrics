package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api

import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange

interface PressFreedomService : IndexFeatureService<PressFreedomValue> {
    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): FeatureMedianRange

    /**
     * Returns political context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getPCRange(): FeatureMedianRange

    /**
     * Returns economic context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getECRange(): FeatureMedianRange

    /**
     * Returns legal context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getLCRange(): FeatureMedianRange

    /**
     * Returns social context range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getSCRange(): FeatureMedianRange

    /**
     * Returns safety range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getSRange(): FeatureMedianRange
}
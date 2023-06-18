package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api

import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexOverviewService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange

interface PopulationService : IndexFeatureService<PopulationIndexValue>,
    IndexOverviewService {
    /**
     * Returns total population range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getTotalPopulationRange(): FeatureMedianRange

    /**
     * Returns male population range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getMalePopulationRange(): FeatureMedianRange

    /**
     * Returns female population range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getFemalePopulationRange(): FeatureMedianRange

    /**
     * Returns population density range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getPopulationDensityRange(): FeatureMedianRange
}
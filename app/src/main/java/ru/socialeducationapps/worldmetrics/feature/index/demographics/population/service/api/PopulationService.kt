package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api

import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange

interface PopulationService : IndexFeatureService<PopulationIndexValue> {
    /**
     * Returns total population range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getTotalPopulationRange(): FeatureRange

    /**
     * Returns male population range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getMalePopulationRange(): FeatureRange

    /**
     * Returns female population range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getFemalePopulationRange(): FeatureRange

    /**
     * Returns population density range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getPopulationDensityRange(): FeatureRange
}
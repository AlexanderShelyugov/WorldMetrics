package ru.socialeducationapps.worldmetrics.modules.demographics.population.service.api

import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexFeatureService

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
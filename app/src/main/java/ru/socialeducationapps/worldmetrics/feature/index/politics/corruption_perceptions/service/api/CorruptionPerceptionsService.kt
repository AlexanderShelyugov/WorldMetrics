package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api

import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange

interface CorruptionPerceptionsService : IndexFeatureService<CorruptionPerceptionsValue> {
    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getValueRange(): FeatureRange
}
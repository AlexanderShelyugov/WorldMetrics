package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api

import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexOverviewService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange

interface CorruptionPerceptionsService : IndexFeatureService<CorruptionPerceptionsValue>,
    IndexOverviewService {
    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    suspend fun getValueRange(): FeatureMedianRange
}
package ru.alexander.worldmetrics.modules.corruption_perceptions.service.api

import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.modules.indexes.model.FeatureRange
import ru.alexander.worldmetrics.modules.indexes.service.api.IndexFeatureService

interface CorruptionPerceptionsService : IndexFeatureService<CorruptionPerceptionsValue> {
    /**
     * Returns value range
     *
     * @return pair of values, where first is minimum value and second is maximum value
     */
    fun getValueRange(): FeatureRange
}
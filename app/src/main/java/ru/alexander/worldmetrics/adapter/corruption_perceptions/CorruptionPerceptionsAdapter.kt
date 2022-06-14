package ru.alexander.worldmetrics.adapter.corruption_perceptions

import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.model.corruption_perceptions.CorruptionPerceptionsData
import ru.alexander.worldmetrics.model.corruption_perceptions.CorruptionPerceptionsData.Companion.INDEXES_TO_SHOW
import ru.alexander.worldmetrics.model.corruption_perceptions.CorruptionPerceptionsData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.model.corruption_perceptions.CorruptionPerceptionsValue

class CorruptionPerceptionsAdapter : IndexFeaturesRVAdapter<CorruptionPerceptionsValue>() {
    override fun getFeatureName(index: Int): Int = INDEXES_TO_SHOW[index].first

    override fun getFeatureExtractors(index: Int):
            Pair<(CorruptionPerceptionsValue) -> Float, (CorruptionPerceptionsValue) -> Float> =
        YEAR_FUNCTION to INDEXES_TO_SHOW[index].second

    override fun getFeaturesNumber(): Int = CorruptionPerceptionsData.FEATURES_NUMBER
}
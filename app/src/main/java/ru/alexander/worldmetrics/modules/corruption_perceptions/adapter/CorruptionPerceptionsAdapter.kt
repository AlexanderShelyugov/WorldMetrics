package ru.alexander.worldmetrics.modules.corruption_perceptions.adapter

import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData.Companion.FEATURES_TO_SHOW
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue

class CorruptionPerceptionsAdapter : IndexFeaturesRVAdapter<CorruptionPerceptionsValue>() {
    override fun getFeatureName(index: Int): Int = FEATURES_TO_SHOW[index].first

    override fun getFeatureExtractors(index: Int):
            Pair<(CorruptionPerceptionsValue) -> Float, (CorruptionPerceptionsValue) -> Float> =
        YEAR_FUNCTION to FEATURES_TO_SHOW[index].second

    override fun getFeaturesNumber(): Int = CorruptionPerceptionsData.FEATURES_NUMBER
}
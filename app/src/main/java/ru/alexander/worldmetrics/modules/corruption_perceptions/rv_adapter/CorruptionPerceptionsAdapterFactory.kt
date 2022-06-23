package ru.alexander.worldmetrics.modules.corruption_perceptions.rv_adapter

import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData.Companion.FEATURES_NUMBER
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData.Companion.FEATURES_TO_SHOW
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue

class CorruptionPerceptionsAdapterFactory private constructor() {
    companion object {
        fun getCorruptionPerceptionsFeaturesAdapter(): IndexFeaturesRVAdapter<CorruptionPerceptionsValue> =
            IndexFeaturesRVAdapter(
                { index -> FEATURES_TO_SHOW[index].first },
                { index -> YEAR_FUNCTION to FEATURES_TO_SHOW[index].second },
                FEATURES_NUMBER,
            )
    }
}
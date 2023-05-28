package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsData.Companion.CORRUPTION_PERCEPTIONS_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter.IndexFeaturesRVAdapter

class CorruptionPerceptionsAdapterFactory private constructor() {
    companion object {
        fun getCorruptionPerceptionsFeaturesAdapter(): IndexFeaturesRVAdapter<CorruptionPerceptionsValue> =
            IndexFeaturesRVAdapter(CORRUPTION_PERCEPTIONS_LAYOUT)
    }
}
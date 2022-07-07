package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.rv_adapter

import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.model.CorruptionPerceptionsData.Companion.CORRUPTION_PERCEPTIONS_LAYOUT
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.model.CorruptionPerceptionsValue

class CorruptionPerceptionsAdapterFactory private constructor() {
    companion object {
        fun getCorruptionPerceptionsFeaturesAdapter(): IndexFeaturesRVAdapter<CorruptionPerceptionsValue> =
            IndexFeaturesRVAdapter(CORRUPTION_PERCEPTIONS_LAYOUT)
    }
}
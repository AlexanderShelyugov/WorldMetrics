package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter

class CorruptionPerceptionsAdapterFactory private constructor() {
    companion object {
        fun getCorruptionPerceptionsFeaturesAdapter(): LabelValueChartRVAdapter<CorruptionPerceptionsValue> =
            LabelValueChartRVAdapter()
    }
}
package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter

class DemocracyIndexAdapterFactory private constructor() {
    companion object {
        fun getDemocracyIndexFeaturesAdapter(): LabelValueChartRVAdapter<DemocracyIndexValue> =
            LabelValueChartRVAdapter()
    }
}
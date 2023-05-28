package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexValue

class DemocracyIndexAdapterFactory private constructor() {
    companion object {
        fun getDemocracyIndexFeaturesAdapter(): LabelValueChartRVAdapter<DemocracyIndexValue> =
            LabelValueChartRVAdapter()
    }
}
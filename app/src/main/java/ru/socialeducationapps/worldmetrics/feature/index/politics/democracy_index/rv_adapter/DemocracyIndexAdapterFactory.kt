package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexData.Companion.DEMOCRACY_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexValue

class DemocracyIndexAdapterFactory private constructor() {
    companion object {
        fun getDemocracyIndexFeaturesAdapter(): IndexFeaturesRVAdapter<DemocracyIndexValue> =
            IndexFeaturesRVAdapter(DEMOCRACY_INDEX_LAYOUT)
    }
}
package ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.rv_adapter

import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexData.Companion.FEATURES_NUMBER
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexData.Companion.FEATURES_TO_SHOW
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexData.Companion.YEAR_FUNCTION
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexValue

class DemocracyIndexAdapterFactory private constructor() {
    companion object {
        fun getDemocracyIndexFeaturesAdapter(): IndexFeaturesRVAdapter<DemocracyIndexValue> =
            IndexFeaturesRVAdapter(
                { index -> FEATURES_TO_SHOW[index].first },
                { index -> YEAR_FUNCTION to FEATURES_TO_SHOW[index].second },
                FEATURES_NUMBER,
            )
    }
}
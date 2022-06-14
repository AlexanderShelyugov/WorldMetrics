package ru.alexander.worldmetrics.adapter.democracy_index

import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.model.democracy_index.DemocracyIndexData.Companion.FEATURES_NUMBER
import ru.alexander.worldmetrics.model.democracy_index.DemocracyIndexData.Companion.FEATURES_TO_SHOW
import ru.alexander.worldmetrics.model.democracy_index.DemocracyIndexData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.model.democracy_index.DemocracyIndexValue

class DemocracyIndexAdapter : IndexFeaturesRVAdapter<DemocracyIndexValue>() {
    override fun getFeatureName(index: Int): Int = FEATURES_TO_SHOW[index].first

    override fun getFeatureExtractors(index: Int):
            Pair<(DemocracyIndexValue) -> Float, (DemocracyIndexValue) -> Float> =
        YEAR_FUNCTION to FEATURES_TO_SHOW[index].second

    override fun getFeaturesNumber(): Int = FEATURES_NUMBER
}
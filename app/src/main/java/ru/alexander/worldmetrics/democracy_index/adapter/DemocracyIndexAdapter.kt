package ru.alexander.worldmetrics.democracy_index.adapter

import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.democracy_index.model.DemocracyIndexData.Companion.FEATURES_NUMBER
import ru.alexander.worldmetrics.democracy_index.model.DemocracyIndexData.Companion.FEATURES_TO_SHOW
import ru.alexander.worldmetrics.democracy_index.model.DemocracyIndexData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.democracy_index.model.DemocracyIndexValue

class DemocracyIndexAdapter : IndexFeaturesRVAdapter<DemocracyIndexValue>() {
    override fun getFeatureName(index: Int): Int = FEATURES_TO_SHOW[index].first

    override fun getFeatureExtractors(index: Int):
            Pair<(DemocracyIndexValue) -> Float, (DemocracyIndexValue) -> Float> =
        YEAR_FUNCTION to FEATURES_TO_SHOW[index].second

    override fun getFeaturesNumber(): Int = FEATURES_NUMBER
}
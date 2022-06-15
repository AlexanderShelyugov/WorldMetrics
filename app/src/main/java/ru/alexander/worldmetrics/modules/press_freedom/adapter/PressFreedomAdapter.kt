package ru.alexander.worldmetrics.modules.press_freedom.adapter

import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomData.Companion.FEATURES_NUMBER
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomData.Companion.INDEXES_TO_SHOW
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomValue

class PressFreedomAdapter : IndexFeaturesRVAdapter<PressFreedomValue>() {
    override fun getFeatureName(index: Int): Int = INDEXES_TO_SHOW[index].first

    override fun getFeatureExtractors(index: Int): Pair<(PressFreedomValue) -> Float, (PressFreedomValue) -> Float> =
        YEAR_FUNCTION to INDEXES_TO_SHOW[index].second

    override fun getFeaturesNumber(): Int = FEATURES_NUMBER
}
package ru.alexander.worldmetrics.modules.press_freedom.rv_adapter

import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomData.Companion.FEATURES_NUMBER
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomData.Companion.FEATURES_TO_SHOW
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomValue

class PressFreedomAdapterFactory private constructor() {
    companion object {
        fun getPressFreedomFeaturesAdapter(): IndexFeaturesRVAdapter<PressFreedomValue> =
            IndexFeaturesRVAdapter(
                { index -> FEATURES_TO_SHOW[index].first },
                { index -> YEAR_FUNCTION to FEATURES_TO_SHOW[index].second },
                FEATURES_NUMBER,
            )
    }
}
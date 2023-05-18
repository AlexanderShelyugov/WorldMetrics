package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomData.Companion.PRESS_FREEDOM_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue

class PressFreedomAdapterFactory private constructor() {
    companion object {
        fun getPressFreedomFeaturesAdapter(): IndexFeaturesRVAdapter<PressFreedomValue> =
            IndexFeaturesRVAdapter(PRESS_FREEDOM_INDEX_LAYOUT)
    }
}
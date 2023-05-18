package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.indexes.common.rv_adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPData.Companion.GDP_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue

class GDPAdapterFactory private constructor() {
    companion object {
        fun getGDPAdapter(): IndexFeaturesRVAdapter<GDPValue> =
            IndexFeaturesRVAdapter(GDP_INDEX_LAYOUT)
    }
}
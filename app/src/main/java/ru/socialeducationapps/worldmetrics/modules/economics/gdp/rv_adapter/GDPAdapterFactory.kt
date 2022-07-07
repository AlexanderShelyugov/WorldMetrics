package ru.socialeducationapps.worldmetrics.modules.economics.gdp.rv_adapter

import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPData.Companion.GDP_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPValue

class GDPAdapterFactory private constructor() {
    companion object {
        fun getGDPAdapter(): IndexFeaturesRVAdapter<GDPValue> =
            IndexFeaturesRVAdapter(GDP_INDEX_LAYOUT)
    }
}
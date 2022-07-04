package ru.socialeducationapps.worldmetrics.modules.economics.gdp.rv_adapter

import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPData.Companion.FEATURES_NUMBER
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPData.Companion.FEATURES_TO_SHOW
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPData.Companion.YEAR_FUNCTION
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPValue

class GDPAdapterFactory private constructor() {
    companion object {
        fun getGDPAdapter(): IndexFeaturesRVAdapter<GDPValue> =
            IndexFeaturesRVAdapter(
                { index -> FEATURES_TO_SHOW[index].first },
                { index -> YEAR_FUNCTION to FEATURES_TO_SHOW[index].second },
                FEATURES_NUMBER,
            )
    }
}
package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue

class GDPAdapterFactory private constructor() {
    companion object {
        fun getGDPAdapter(): LabelValueChartRVAdapter<GDPValue> =
            LabelValueChartRVAdapter()
    }
}
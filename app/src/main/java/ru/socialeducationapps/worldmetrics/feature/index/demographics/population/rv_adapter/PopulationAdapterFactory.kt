package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.rv_adapter

import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexValue

class PopulationAdapterFactory private constructor() {
    companion object {
        fun getPopulationAdapter(): LabelValueChartRVAdapter<PopulationIndexValue> =
            LabelValueChartRVAdapter()
    }
}
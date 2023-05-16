package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.rv_adapter

import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexData.Companion.POPULATION_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexValue

class PopulationAdapterFactory private constructor() {
    companion object {
        fun getPopulationAdapter(): IndexFeaturesRVAdapter<PopulationIndexValue> =
            IndexFeaturesRVAdapter(POPULATION_INDEX_LAYOUT)
    }
}
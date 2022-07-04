package ru.socialeducationapps.worldmetrics.modules.demographics.population.rv_adapter

import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexData.Companion.FEATURES_NUMBER
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexData.Companion.FEATURES_TO_SHOW
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexData.Companion.YEAR_FUNCTION
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexValue

class PopulationAdapterFactory private constructor() {
    companion object {
        fun getPopulationAdapter(): IndexFeaturesRVAdapter<PopulationIndexValue> =
            IndexFeaturesRVAdapter(
                { index -> FEATURES_TO_SHOW[index].first },
                { index -> YEAR_FUNCTION to FEATURES_TO_SHOW[index].second },
                FEATURES_NUMBER,
            )
    }
}
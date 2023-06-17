package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureDataDescriptor
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout

class PopulationIndexData private constructor() {
    companion object {
        val POPULATION_INDEX_LAYOUT = IndexFeaturesLayout<PopulationIndexValue>(
            { it.year.toFloat() },
            listOf(
                FeatureDataDescriptor(
                    R.string.population_population_total,
                    { it.populationTotal }),
                FeatureDataDescriptor(
                    R.string.population_population_male,
                    { it.populationMale }),
                FeatureDataDescriptor(
                    R.string.population_population_female,
                    { it.populationFemale }),
                FeatureDataDescriptor(
                    R.string.population_population_density,
                    { it.populationDensity }),
            )
        )
    }
}
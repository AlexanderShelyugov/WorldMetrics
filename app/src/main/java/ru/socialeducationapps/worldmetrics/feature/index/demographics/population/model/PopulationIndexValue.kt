package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model

import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue

data class PopulationIndexValue(
    val iso3Code: String,
    val year: Int,
    val populationTotal: FeatureValue,
    val populationMale: FeatureValue,
    val populationFemale: FeatureValue,
    val populationDensity: FeatureValue,
)

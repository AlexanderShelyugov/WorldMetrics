package ru.socialeducationapps.worldmetrics.modules.demographics.population.model

data class PopulationIndexValue(
    val iso3Code: String,
    val year: Int,
    val populationTotal: Float,
    val populationMale: Float,
    val populationFemale: Float,
    val populationDensity: Float,
)

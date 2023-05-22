package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model

import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue

data class GDPValue(
    val iso3Code: String,
    val year: Int,
    val valueMlnUsd: FeatureValue,
    val valueUsdCap: FeatureValue,
)
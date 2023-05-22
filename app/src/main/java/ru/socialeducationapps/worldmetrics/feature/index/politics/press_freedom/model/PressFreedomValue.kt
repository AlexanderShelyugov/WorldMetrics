package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model

import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue

data class PressFreedomValue constructor(
    val countryCode: String,
    val score: FeatureValue,
    val politicalContext: FeatureValue,
    val economicContext: FeatureValue,
    val legalContext: FeatureValue,
    val socialContext: FeatureValue,
    val safety: FeatureValue,
    val year: Int,
)
package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model

import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue

data class CorruptionPerceptionsValue(
    val country: String,
    val year: Int,
    val value: FeatureValue
)
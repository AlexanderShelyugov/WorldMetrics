package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.model

data class CorruptionPerceptionsValue(
    val country: String,
    val year: Int,
    val value: Float
)
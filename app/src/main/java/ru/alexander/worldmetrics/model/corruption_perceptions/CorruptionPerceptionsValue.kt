package ru.alexander.worldmetrics.model.corruption_perceptions

data class CorruptionPerceptionsValue(
    val country: String,
    val year: Int,
    val value: Float
)
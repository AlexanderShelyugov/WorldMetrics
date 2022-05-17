package ru.alexander.worldmetrics.model

data class PressFreedomValue constructor(
    val countryCode: String,
    val countryName: String,
    val score: Float,
    val politicalContext: Float,
    val economicContext: Float,
    val legalContext: Float,
    val socialContext: Float,
    val safety: Float,
    val year: Int,
)
package ru.alexander.worldmetrics.model

data class DemocracyIndexValue(
    val countryCode: String,
    val country: String,
    val year: Int,
    val democracyIndex: Float,
    val electoralProcessAndPluralism: Float,
    val functioningOfGovernment: Float,
    val politicalParticipation: Float,
    val politicalCulture: Float,
    val civilLiberties: Float,
)
package ru.socialeducationapps.worldmetrics.modules.democracy_index.model

data class DemocracyIndexValue(
    val countryCode: String,
    val year: Int,
    val democracyIndex: Float,
    val electoralProcessAndPluralism: Float,
    val functioningOfGovernment: Float,
    val politicalParticipation: Float,
    val politicalCulture: Float,
    val civilLiberties: Float,
)
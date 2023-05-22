package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model

import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue

data class DemocracyIndexValue(
    val countryCode: String,
    val year: Int,
    val democracyIndex: FeatureValue,
    val electoralProcessAndPluralism: FeatureValue,
    val functioningOfGovernment: FeatureValue,
    val politicalParticipation: FeatureValue,
    val politicalCulture: FeatureValue,
    val civilLiberties: FeatureValue,
)
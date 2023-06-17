package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureDataDescriptor
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout

class DemocracyIndexData private constructor() {
    companion object {
        val DEMOCRACY_INDEX_LAYOUT = IndexFeaturesLayout<DemocracyIndexValue>(
            { it.year.toFloat() },
            listOf(
                FeatureDataDescriptor(R.string.index_name_democracy, { it.democracyIndex }),
                FeatureDataDescriptor(
                    R.string.electoral_process_and_pluralism,
                    { it.electoralProcessAndPluralism }),
                FeatureDataDescriptor(
                    R.string.functioning_of_government,
                    { it.functioningOfGovernment }),
                FeatureDataDescriptor(
                    R.string.political_participation,
                    { it.politicalParticipation }),
                FeatureDataDescriptor(R.string.political_culture, { it.politicalCulture }),
                FeatureDataDescriptor(R.string.civil_liberties, { it.civilLiberties }),
            )
        )
    }

}
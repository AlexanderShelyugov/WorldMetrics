package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout

class DemocracyIndexData private constructor() {
    companion object {
        val DEMOCRACY_INDEX_LAYOUT = IndexFeaturesLayout<DemocracyIndexValue>(
            { it.year.toFloat() },
            listOf(
                R.string.index_name_democracy to { it.democracyIndex },
                R.string.electoral_process_and_pluralism to { it.electoralProcessAndPluralism },
                R.string.functioning_of_government to { it.functioningOfGovernment },
                R.string.political_participation to { it.politicalParticipation },
                R.string.political_culture to { it.politicalCulture },
                R.string.civil_liberties to { it.civilLiberties },
            )
        )
    }

}
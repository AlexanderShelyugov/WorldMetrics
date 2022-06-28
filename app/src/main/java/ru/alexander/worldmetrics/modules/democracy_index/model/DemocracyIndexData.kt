package ru.alexander.worldmetrics.modules.democracy_index.model

import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.modules.indexes.model.FeatureExtractor

private typealias Index = DemocracyIndexValue
private typealias ValueFunction = (Index) -> Float

class DemocracyIndexData private constructor() {
    companion object {
        val YEAR_FUNCTION: FeatureExtractor<Index> = { it.year.toFloat() }

        val FEATURES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.index_name_democracy to { it.democracyIndex },
            R.string.electoral_process_and_pluralism to { it.electoralProcessAndPluralism },
            R.string.functioning_of_government to { it.functioningOfGovernment },
            R.string.political_participation to { it.politicalParticipation },
            R.string.political_culture to { it.politicalCulture },
            R.string.civil_liberties to { it.civilLiberties },
        )

        val FEATURES_NUMBER = FEATURES_TO_SHOW.size
    }

}
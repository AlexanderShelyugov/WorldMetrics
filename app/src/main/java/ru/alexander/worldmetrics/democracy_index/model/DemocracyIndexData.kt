package ru.alexander.worldmetrics.democracy_index.model

import ru.alexander.worldmetrics.R

private typealias Index = DemocracyIndexValue
private typealias ValueFunction = (Index) -> Float

class DemocracyIndexData private constructor() {
    companion object {
        const val FEATURES_NUMBER = 6

        val YEAR_FUNCTION: ValueFunction = { it.year.toFloat() }

        val FEATURES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.democracy_index_name to { it.democracyIndex },
            R.string.electoral_process_and_pluralism to { it.electoralProcessAndPluralism },
            R.string.functioning_of_government to { it.functioningOfGovernment },
            R.string.political_participation to { it.politicalParticipation },
            R.string.political_culture to { it.politicalCulture },
            R.string.civil_liberties to { it.civilLiberties },
        )
    }

}
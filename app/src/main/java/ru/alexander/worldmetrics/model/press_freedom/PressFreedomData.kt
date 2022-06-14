package ru.alexander.worldmetrics.model.press_freedom

import ru.alexander.worldmetrics.R

private typealias Index = PressFreedomValue
private typealias ValueFunction = (Index) -> Float

class PressFreedomData private constructor() {
    companion object {
        const val FEATURES_NUMBER = 6

        val YEAR_FUNCTION: ValueFunction = { it.year.toFloat() }

        val INDEXES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.press_freedom_index_name to { it.score },
            R.string.press_freedom_political_context to { it.politicalContext },
            R.string.press_freedom_economic_context to { it.economicContext },
            R.string.press_freedom_legal_context to { it.legalContext },
            R.string.press_freedom_social_context to { it.socialContext },
            R.string.press_freedom_safety to { it.safety },
        )
    }

}
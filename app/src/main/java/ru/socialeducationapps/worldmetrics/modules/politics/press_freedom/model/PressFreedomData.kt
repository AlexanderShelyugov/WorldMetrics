package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.model

import ru.socialeducationapps.worldmetrics.R

private typealias Index = PressFreedomValue
private typealias ValueFunction = (Index) -> Float

class PressFreedomData private constructor() {
    companion object {
        val YEAR_FUNCTION: ValueFunction = { it.year.toFloat() }

        val FEATURES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.index_name_press_freedom to { it.score },
            R.string.press_freedom_political_context to { it.politicalContext },
            R.string.press_freedom_economic_context to { it.economicContext },
            R.string.press_freedom_legal_context to { it.legalContext },
            R.string.press_freedom_social_context to { it.socialContext },
            R.string.press_freedom_safety to { it.safety },
        )

        val FEATURES_NUMBER = FEATURES_TO_SHOW.size
    }

}
package ru.alexander.worldmetrics.modules.corruption_perceptions.model

import ru.alexander.worldmetrics.R

private typealias Index = CorruptionPerceptionsValue
private typealias ValueFunction = (Index) -> Float

class CorruptionPerceptionsData private constructor() {

    companion object {
        val YEAR_FUNCTION: ValueFunction = { it.year.toFloat() }

        val FEATURES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.corruption_perceptions_index_name to { it.value },
        )

        val FEATURES_NUMBER = FEATURES_TO_SHOW.size
    }
}
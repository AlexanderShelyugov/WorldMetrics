package ru.alexander.worldmetrics.modules.corruption_perceptions.model

import ru.alexander.worldmetrics.R

private typealias Index = CorruptionPerceptionsValue
private typealias ValueFunction = (Index) -> Float

class CorruptionPerceptionsData private constructor() {

    companion object {
        const val FEATURES_NUMBER = 1

        val YEAR_FUNCTION: ValueFunction = { it.year.toFloat() }

        val INDEXES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.corruption_perceptions_index_name to { it.value },
        )
    }
}
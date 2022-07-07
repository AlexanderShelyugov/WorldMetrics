package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.indexes.model.CommonIndexLayout

private typealias Index = CorruptionPerceptionsValue
private typealias ValueFunction = (Index) -> Float

class CorruptionPerceptionsData private constructor() {
    companion object {
        val CORRUPTION_PERCEPTIONS_LAYOUT = CommonIndexLayout<CorruptionPerceptionsValue>(
            { it.year.toFloat() },
            listOf(
                R.string.index_name_corruption_perceptions to { it.value },
            )
        )
    }
}
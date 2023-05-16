package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CommonIndexLayout

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
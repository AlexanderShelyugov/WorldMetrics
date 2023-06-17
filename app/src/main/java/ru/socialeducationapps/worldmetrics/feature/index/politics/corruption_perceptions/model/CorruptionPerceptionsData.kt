package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureDataDescriptor
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout

class CorruptionPerceptionsData private constructor() {
    companion object {
        val CORRUPTION_PERCEPTIONS_LAYOUT = IndexFeaturesLayout<CorruptionPerceptionsValue>(
            { it.year.toFloat() },
            listOf(
                FeatureDataDescriptor(R.string.index_name_corruption_perceptions, { it.value }),
            )
        )
    }
}
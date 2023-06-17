package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureDataDescriptor
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout

class PressFreedomData private constructor() {
    companion object {
        val PRESS_FREEDOM_INDEX_LAYOUT = IndexFeaturesLayout<PressFreedomValue>(
            { it.year.toFloat() },
            listOf(
                FeatureDataDescriptor(R.string.index_name_press_freedom, { it.score }),
                FeatureDataDescriptor(
                    R.string.press_freedom_political_context,
                    { it.politicalContext }),
                FeatureDataDescriptor(
                    R.string.press_freedom_economic_context,
                    { it.economicContext }),
                FeatureDataDescriptor(R.string.press_freedom_legal_context, { it.legalContext }),
                FeatureDataDescriptor(R.string.press_freedom_social_context, { it.socialContext }),
                FeatureDataDescriptor(R.string.press_freedom_safety, { it.safety }),
            )
        )
    }
}
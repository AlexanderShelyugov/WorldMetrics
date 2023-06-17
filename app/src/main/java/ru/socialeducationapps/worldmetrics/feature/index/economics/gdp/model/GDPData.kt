package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureDataDescriptor
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout

class GDPData private constructor() {
    companion object {
        val GDP_INDEX_LAYOUT = IndexFeaturesLayout<GDPValue>(
            { it.year.toFloat() },
            listOf(
                FeatureDataDescriptor(R.string.gdp_millions_usd, { it.valueMlnUsd }),
                FeatureDataDescriptor(R.string.gdp_usd_per_capita, { it.valueUsdCap }),
            )
        )
    }
}
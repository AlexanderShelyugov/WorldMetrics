package ru.socialeducationapps.worldmetrics.modules.economics.gdp.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureExtractor

private typealias Index = GDPValue
private typealias ValueFunction = (Index) -> Float

class GDPData private constructor() {
    companion object {
        val YEAR_FUNCTION: FeatureExtractor<Index> = { it.year.toFloat() }

        val FEATURES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.gdp_millions_usd to { it.valueMlnUsd },
            R.string.gdp_usd_per_capita to { it.valueUsdCap },
        )

        val FEATURES_NUMBER = FEATURES_TO_SHOW.size
    }
}
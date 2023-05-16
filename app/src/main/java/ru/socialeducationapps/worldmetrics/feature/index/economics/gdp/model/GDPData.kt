package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CommonIndexLayout

private typealias Index = GDPValue
private typealias ValueFunction = (Index) -> Float

class GDPData private constructor() {
    companion object {
        val GDP_INDEX_LAYOUT = CommonIndexLayout<GDPValue>(
            { it.year.toFloat() },
            listOf(
                R.string.gdp_millions_usd to { it.valueMlnUsd },
                R.string.gdp_usd_per_capita to { it.valueUsdCap },
            )
        )
    }
}
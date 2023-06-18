package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api

import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexOverviewService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange

interface GDPService : IndexFeatureService<GDPValue>, IndexOverviewService {
    fun getValueMlnUsdRange(): FeatureMedianRange
    fun getValueUsdPerCapitaRange(): FeatureMedianRange
}
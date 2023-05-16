package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api

import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange

interface GDPService : IndexFeatureService<GDPValue> {
    fun getValueMlnUsdRange(): FeatureRange
    fun getValueUsdPerCapitaRange(): FeatureRange
}
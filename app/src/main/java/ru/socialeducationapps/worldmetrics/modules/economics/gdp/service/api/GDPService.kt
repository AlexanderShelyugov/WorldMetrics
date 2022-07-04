package ru.socialeducationapps.worldmetrics.modules.economics.gdp.service.api

import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexFeatureService

interface GDPService : IndexFeatureService<GDPValue> {
    fun getValueMlnUsdRange(): FeatureRange
    fun getValueUsdPerCapitaRange(): FeatureRange
}
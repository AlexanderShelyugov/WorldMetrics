package ru.socialeducationapps.worldmetrics.modules.gdp.service.api

import ru.socialeducationapps.worldmetrics.modules.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexFeatureService

interface GDPService : IndexFeatureService<GDPValue> {
    fun getValueMlnUsdRange(): FeatureRange
    fun getValueUsdPerCapitaRange(): FeatureRange
}
package ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel

import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.SimpleCountryValue

interface OverviewViewModel {
    val lastYearData: Flow<List<SimpleCountryValue>?>
    fun getValueRange(): FeatureRange
    fun getMedian(): FeatureValue
}
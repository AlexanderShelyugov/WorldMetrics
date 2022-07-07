package ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel

import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

interface OverviewViewModel {
    val lastYearData: Flow<List<SimpleCountryValue>?>
    fun getValueRange(): FeatureRange
}
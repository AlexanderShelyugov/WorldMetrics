package ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange

interface CountryDetailViewModel<T> {
    val lastYearData: LiveData<T>
    val allData: Flow<List<T>>
    fun setCountry(country: String)
    fun getFeatureRanges(): List<FeatureRange>
}
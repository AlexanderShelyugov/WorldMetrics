package ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.model.CommonIndexLayout
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexFeatureService

abstract class CommonCountryDetailViewModel<Index> constructor(
    private val service: IndexFeatureService<Index>,
    private val dispatchers: DispatcherProvider,
    val indexLayout: CommonIndexLayout<Index>,
) : ViewModel(), CountryDetailViewModel<Index> {
    private var country: String = ""
    private val _lastYearData = MutableLiveData<Index>()
    private val _allData = MutableStateFlow<List<Index>>(emptyList())

    final override val lastYearData: LiveData<Index>
        get() = _lastYearData
            .also { loadLastYearData() }
    final override val allData: Flow<List<Index>>
        get() = _allData
            .also { loadAllData() }
            .asStateFlow()

    final override fun setCountry(country: String) {
        this.country = country
    }

    final override fun getFeatureRanges() = indexLayout.features.asSequence()
        .map { feature -> feature.first }
        .map { featureName ->
            getFeatureExtractors()[featureName]!!(service)
        }
        .toList()

    private fun loadLastYearData() {
        viewModelScope.launch(dispatchers.io) {
            _lastYearData.value = service.getLastYearData(country)
        }
    }

    private fun loadAllData() {
        viewModelScope.launch(dispatchers.io) {
            _allData.value = service.getAllData(country)
        }
    }

    protected abstract fun getFeatureExtractors(): Map<Int, (IndexFeatureService<Index>) -> FeatureRange>
}
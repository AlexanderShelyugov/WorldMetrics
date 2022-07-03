package ru.socialeducationapps.worldmetrics.modules.gdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.gdp.model.GDPData.Companion.FEATURES_TO_SHOW
import ru.socialeducationapps.worldmetrics.modules.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.modules.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import javax.inject.Inject

@HiltViewModel
class GDPCountryDetailViewModel @Inject constructor(
    private val service: GDPService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    private var country: String = ""
    private val _lastYearData = MutableLiveData<GDPValue>()
    private val _allData = MutableStateFlow<List<GDPValue>>(emptyList())

    val lastYearData: LiveData<GDPValue>
        get() = _lastYearData
            .also { loadLastYearData() }

    val allData: Flow<List<GDPValue>>
        get() = _allData
            .also { loadAllData() }
            .asStateFlow()

    fun setCountry(country: String) {
        this.country = country
    }

    fun getFeatureRanges(countryCode: String): List<FeatureRange> =
        FEATURES_TO_SHOW.asSequence()
            .map { feature ->
                FEATURE_RANGE_EXTRACTORS[feature.first]!!.invoke(service)
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

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (GDPService) -> FeatureRange>(
            R.string.gdp_millions_usd to { it.getValueMlnUsdRange() },
            R.string.gdp_usd_per_capita to { it.getValueUsdPerCapitaRange() }
        )
    }
}
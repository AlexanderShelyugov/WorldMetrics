package ru.socialeducationapps.worldmetrics.modules.demographics.population.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexData
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.modules.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import javax.inject.Inject

private typealias Index = PopulationIndexValue

@HiltViewModel
class PopulationCountryDetailViewModel @Inject constructor(
    private val service: PopulationService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    private var country: String = ""
    private val _lastYearData = MutableLiveData<Index>()
    private val _allData = MutableStateFlow<List<Index>>(emptyList())

    val lastYearData: LiveData<Index>
        get() = _lastYearData
            .also { loadLastYearData() }

    val allData: Flow<List<Index>>
        get() = _allData
            .also { loadAllData() }
            .asStateFlow()

    fun setCountry(country: String) {
        this.country = country
    }

    fun getFeatureRanges(countryCode: String): List<FeatureRange> =
        PopulationIndexData.FEATURES_TO_SHOW.asSequence()
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
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (PopulationService) -> FeatureRange>(
            R.string.population_population_total to { runBlocking { it.getTotalPopulationRange() } },
            R.string.population_population_female to { runBlocking { it.getFemalePopulationRange() } },
            R.string.population_population_male to { runBlocking { it.getMalePopulationRange() } },
            R.string.population_population_density to { runBlocking { it.getPopulationDensityRange() } },
        )
    }
}
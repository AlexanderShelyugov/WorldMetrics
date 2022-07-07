package ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.viewmodel

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
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexData
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.service.api.DemocracyIndexService
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexCountryDetailViewModel @Inject constructor(
    private val service: DemocracyIndexService,
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    private var country: String = ""
    private val _lastYearData = MutableLiveData<DemocracyIndexValue>()
    private val _allData = MutableStateFlow<List<DemocracyIndexValue>>(emptyList())

    val lastYearData: LiveData<DemocracyIndexValue>
        get() = _lastYearData
            .also { loadLastYearData() }

    val allData: Flow<List<DemocracyIndexValue>>
        get() = _allData
            .also { loadAllData() }
            .asStateFlow()

    fun setCountry(country: String) {
        this.country = country
    }

    fun getFeatureRanges(countryCode: String) =
        DemocracyIndexData.DEMOCRACY_INDEX_LAYOUT.features.asSequence()
            .map { feature -> feature.first }
            .map { featureName -> FEATURE_RANGE_EXTRACTORS[featureName]!!(service) }
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
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (DemocracyIndexService) -> FeatureRange>(
            R.string.index_name_democracy to { it.getValueRange() },
            R.string.electoral_process_and_pluralism to { it.getEPAPRange() },
            R.string.functioning_of_government to { it.getFOGRange() },
            R.string.political_participation to { it.getPPRange() },
            R.string.political_culture to { it.getPCRange() },
            R.string.civil_liberties to { it.getCLRange() },
        )
    }
}
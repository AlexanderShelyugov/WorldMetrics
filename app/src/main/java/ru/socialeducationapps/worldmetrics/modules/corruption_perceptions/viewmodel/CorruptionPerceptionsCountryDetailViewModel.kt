package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData.Companion.FEATURES_TO_SHOW
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsCountryDetailViewModel @Inject constructor(
    private val service: CorruptionPerceptionsService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    private var country: String = ""
    private val _allData = MutableStateFlow<List<CorruptionPerceptionsValue>>(emptyList())

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val allData: Flow<List<CorruptionPerceptionsValue>> = _allData
        .also { loadData() }
        .asStateFlow()

    fun getFeatureRanges(countryCode: String): List<FeatureRange> = FEATURES_TO_SHOW.asSequence()
        .map { feature ->
            FEATURE_RANGE_EXTRACTORS[feature.first]!!.invoke(service)
        }
        .toList()


    private fun loadData() {
        viewModelScope.launch(dispatchers.io) {
            _allData.value = service.getAllData(country)
        }
    }

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (CorruptionPerceptionsService) -> FeatureRange>(
            R.string.index_name_corruption_perceptions to { runBlocking { it.getValueRange() } }
        )
    }

}
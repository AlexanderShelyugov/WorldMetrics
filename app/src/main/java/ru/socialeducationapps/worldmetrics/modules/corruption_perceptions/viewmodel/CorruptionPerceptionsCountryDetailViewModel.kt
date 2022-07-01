package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _allData = MutableLiveData<List<CorruptionPerceptionsValue>>()

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val allData: LiveData<List<CorruptionPerceptionsValue>> = _allData
        .also { loadData() }

    /*
    fun getFeatureColors(countryCode: String): List<Int> {
        viewModelScope.launch(dispatchers.io) {
            val extractors = FEATURES_TO_SHOW.asSequence()
                .map { feature ->
                    FEATURE_RANGE_EXTRACTORS[feature.first]!!.invoke(service) to feature.second
                }
                .toList()
            val lastYearData = service.getLastYearData(countryCode)
            val colors = extractors.asSequence()
                .map {
                    DEFAULT_COLOR_CALCULATOR.evalColor(
                        it.first, it.second(lastYearData)
                    )
                }
                .toList()
        }


        return colors
    }
     */

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
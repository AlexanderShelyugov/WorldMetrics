package ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.model.PressFreedomData.Companion.FEATURES_TO_SHOW
import ru.socialeducationapps.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomCountryDetailViewModel @Inject constructor(
    private val service: PressFreedomService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    private var country: String = ""
    private val _lastYearData = MutableLiveData<List<SimpleCountryValue>>()
    private val _allData = MutableLiveData<List<PressFreedomValue>>()

    fun setCountry(country: String) {
        this.country = country
    }

    val lastYearData: LiveData<List<SimpleCountryValue>>
        get() = _lastYearData.also { loadLastYearData() }

    val allData: LiveData<List<PressFreedomValue>>
        get() = _allData.also { loadAllData() }

    fun getFeatureRanges(countryCode: String): List<FeatureRange> =
        FEATURES_TO_SHOW.asSequence()
            .map { feature ->
                FEATURE_RANGE_EXTRACTORS[feature.first]!!.invoke(service)
            }
            .toList()

    private fun loadAllData() {
        viewModelScope.launch(dispatchers.io) {
            _allData.value = service.getAllData(country)
        }
    }

    private fun loadLastYearData() {
        viewModelScope.launch(dispatchers.io) {
            _lastYearData.value = service.getLastYearData()
        }
    }

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (PressFreedomService) -> FeatureRange>(
            R.string.index_name_press_freedom to { it.getValueRange() },
            R.string.press_freedom_political_context to { it.getPCRange() },
            R.string.press_freedom_economic_context to { it.getECRange() },
            R.string.press_freedom_legal_context to { it.getLCRange() },
            R.string.press_freedom_social_context to { it.getSCRange() },
            R.string.press_freedom_safety to { it.getSRange() },
        )
    }
}

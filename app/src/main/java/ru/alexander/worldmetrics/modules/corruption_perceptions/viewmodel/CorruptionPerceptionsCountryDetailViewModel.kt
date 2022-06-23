package ru.alexander.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.global.ColorAccess.Companion.DEFAULT_COLOR_CALCULATOR
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsCountryDetailViewModel @Inject constructor(
    private val service: CorruptionPerceptionsService
) : ViewModel() {
    private var country: String = ""
    private val allDataContainer = MutableLiveData<List<CorruptionPerceptionsValue>>()

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val allData: LiveData<List<CorruptionPerceptionsValue>> by lazy {
        allDataContainer
    }

    fun getFeatureColors(countryCode: String): List<Int> {
        val ranges = mapOf<Pair<Float, Float>, (CorruptionPerceptionsValue) -> Float>(
            service.getValueRange() to { it.value }
        )
        val lastYearData = service.getLastYearData(countryCode)
        val colors = ranges.asSequence()
            .map {
                DEFAULT_COLOR_CALCULATOR.evalColor(
                    it.key.first, it.key.second, it.value(lastYearData)
                )
            }
            .toList()
        return colors
    }

    private fun loadData() {
        allDataContainer.value = service.getAllData(country)
    }
}
package ru.alexander.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.global.ColorAccess.Companion.DEFAULT_COLOR_CALCULATOR
import ru.alexander.worldmetrics.modules.indexes.model.FeatureRange
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData.Companion.FEATURES_TO_SHOW
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
        return colors
    }

    private fun loadData() {
        allDataContainer.value = service.getAllData(country)
    }

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (CorruptionPerceptionsService) -> FeatureRange>(
            R.string.index_name_corruption_perceptions to { it.getValueRange() }
        )
    }

}
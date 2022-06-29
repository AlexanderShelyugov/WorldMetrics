package ru.socialeducationapps.worldmetrics.modules.democracy_index.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.global.ColorAccess.Companion.DEFAULT_COLOR_CALCULATOR
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsData
import ru.socialeducationapps.worldmetrics.modules.democracy_index.model.DemocracyIndexData.Companion.FEATURES_TO_SHOW
import ru.socialeducationapps.worldmetrics.modules.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexCountryDetailViewModel @Inject constructor(
    private val service: DemocracyIndexService
) : ViewModel() {
    private var country: String = ""
    private val lastYearDataContainer = MutableLiveData<DemocracyIndexValue>()
    private val allDataContainer = MutableLiveData<List<DemocracyIndexValue>>()

    fun setCountry(country: String) {
        this.country = country
        loadData()
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
                DEFAULT_COLOR_CALCULATOR.evalColor(it.first, it.second(lastYearData))
            }
            .toList()
        return colors
    }

    fun getFeatureRanges(countryCode: String): List<FeatureRange> =
        FEATURES_TO_SHOW.asSequence()
            .map { feature ->
                FEATURE_RANGE_EXTRACTORS[feature.first]!!.invoke(service)
            }
            .toList()

    val lastYearData: LiveData<DemocracyIndexValue> by lazy {
        lastYearDataContainer
    }

    val allData: LiveData<List<DemocracyIndexValue>> by lazy {
        allDataContainer
    }

    private fun loadData() {
        lastYearDataContainer.value = service.getLastYearData(country)
        allDataContainer.value = service.getAllData(country)
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
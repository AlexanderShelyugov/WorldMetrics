package ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.global.ColorAccess
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.model.PressFreedomData
import ru.socialeducationapps.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomCountryDetailViewModel @Inject constructor(
    private val service: PressFreedomService
) : ViewModel() {

    private var country: String = ""
    private val lastYearDataContainer = MutableLiveData<List<SimpleCountryValue>>().also {
        it.value = service.getLastYearData()
    }
    private val allDataContainer = MutableLiveData<List<PressFreedomValue>>()

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val lastYearData: LiveData<List<SimpleCountryValue>> by lazy {
        lastYearDataContainer
    }

    val allData: LiveData<List<PressFreedomValue>> by lazy {
        allDataContainer
    }

    fun getFeatureColors(countryCode: String): List<Int> {
        val extractors = PressFreedomData.FEATURES_TO_SHOW.asSequence()
            .map { feature ->
                FEATURE_RANGE_EXTRACTORS[feature.first]!!.invoke(service) to feature.second
            }
            .toList()
        val lastYearData = service.getLastYearData(countryCode)
        val colors = extractors.asSequence()
            .map {
                ColorAccess.DEFAULT_COLOR_CALCULATOR.evalColor(it.first, it.second(lastYearData))
            }
            .toList()
        return colors
    }

    private fun loadData() {
        lastYearDataContainer.value = service.getLastYearData()
        allDataContainer.value = service.getAllData(country)
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
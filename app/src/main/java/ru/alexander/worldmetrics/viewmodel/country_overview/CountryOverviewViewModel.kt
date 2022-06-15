package ru.alexander.worldmetrics.viewmodel.country_overview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.democracy_index.model.DemocracyIndexValue
import ru.alexander.worldmetrics.model.press_freedom.PressFreedomValue
import ru.alexander.worldmetrics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.alexander.worldmetrics.democracy_index.service.api.DemocracyIndexService
import ru.alexander.worldmetrics.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class CountryOverviewViewModel @Inject constructor(
    private val corruptionPerceptionsService: CorruptionPerceptionsService,
    private val democracyIndexService: DemocracyIndexService,
    private val pressFreedomService: PressFreedomService,
) : ViewModel() {
    fun getDataForCountry(countryCode: String): CountryOverviewData {
        val corruptionPerceptions = corruptionPerceptionsService.getAllData(countryCode)
        val democracyIndex = democracyIndexService.getAllYearData(countryCode)
        val pressFreedom = pressFreedomService.getData(countryCode)
        return CountryOverviewData(
            countryCode,
            corruptionPerceptions,
            democracyIndex,
            pressFreedom,
        )
    }
}

data class CountryOverviewData(
    val countryCode: String,
    val corruptionPerceptions: List<CorruptionPerceptionsValue>,
    val democracyIndex: List<DemocracyIndexValue>,
    val pressFreedom: List<PressFreedomValue>
)
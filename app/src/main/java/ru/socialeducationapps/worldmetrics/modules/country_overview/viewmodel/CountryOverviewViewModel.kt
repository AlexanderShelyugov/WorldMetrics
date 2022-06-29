package ru.socialeducationapps.worldmetrics.viewmodel.country_overview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.modules.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class CountryOverviewViewModel @Inject constructor(
    private val corruptionPerceptionsService: CorruptionPerceptionsService,
    private val democracyIndexService: DemocracyIndexService,
    private val pressFreedomService: PressFreedomService,
) : ViewModel() {
    fun getDataForCountry(countryCode: String): CountryOverviewData {
        val corruptionPerceptions = corruptionPerceptionsService.getAllData(countryCode)
        val democracyIndex = democracyIndexService.getAllData(countryCode)
        val pressFreedom = pressFreedomService.getAllData(countryCode)
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
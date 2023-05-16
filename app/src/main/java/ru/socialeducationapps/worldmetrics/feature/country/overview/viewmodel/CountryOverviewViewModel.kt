package ru.socialeducationapps.worldmetrics.feature.country.overview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class CountryOverviewViewModel @Inject constructor(
    private val corruptionPerceptionsService: CorruptionPerceptionsService,
    private val democracyIndexService: DemocracyIndexService,
    private val pressFreedomService: PressFreedomService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    fun getDataForCountry(countryCode: String): CountryOverviewData {
        val items = mutableListOf<Deferred<Any>>()
        viewModelScope.launch(dispatchers.io) {
            items += async { corruptionPerceptionsService.getAllData(countryCode) }
            items += async { democracyIndexService.getAllData(countryCode) }
            items += async { pressFreedomService.getAllData(countryCode) }
        }
        val result: CountryOverviewData
        runBlocking {
            result = items.awaitAll().let { data ->
                CountryOverviewData(
                    countryCode,
                    data[0] as List<CorruptionPerceptionsValue>,
                    data[1] as List<DemocracyIndexValue>,
                    data[2] as List<PressFreedomValue>,
                )
            }
        }
        return result
    }
}

data class CountryOverviewData(
    val countryCode: String,
    val corruptionPerceptions: List<CorruptionPerceptionsValue>,
    val democracyIndex: List<DemocracyIndexValue>,
    val pressFreedom: List<PressFreedomValue>
)
package ru.socialeducationapps.worldmetrics.feature.countries_comparison.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureDataDescriptor
import javax.inject.Inject

class CountriesComparisonViewModel @Inject constructor(
    dispatchers: DispatcherProvider,
    private val populationService: PopulationService,
    private val democracyIndexService: DemocracyIndexService,
    private val corruptionPerceptionsService: CorruptionPerceptionsService,
    private val pressFreedomService: PressFreedomService,
    private val gdpService: GDPService,
) {
    fun getViewState(): Flow<ViewState> = _viewState

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Initial())

    companion object {
        sealed class ViewState {
            class Initial : ViewState()
            class Loading : ViewState()
            data class Success(val data: CountriesComparisonData) : ViewState()
            data class Error(val exception: Throwable) : ViewState()
        }
    }
}

data class CountriesComparisonData(
    val countryAData: CompleteDataOfCountry,
    val countryBData: CompleteDataOfCountry,
)

data class CompleteDataOfCountry(
    val countryId: Int,
    val indexesData: List<Any>,
)

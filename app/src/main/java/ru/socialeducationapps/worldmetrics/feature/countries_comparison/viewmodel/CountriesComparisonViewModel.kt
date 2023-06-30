package ru.socialeducationapps.worldmetrics.feature.countries_comparison.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexData.Companion.POPULATION_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPData.Companion.GDP_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsData.Companion.CORRUPTION_PERCEPTIONS_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexData.Companion.DEMOCRACY_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomData.Companion.PRESS_FREEDOM_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout
import javax.inject.Inject

@HiltViewModel
class CountriesComparisonViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val populationService: PopulationService,
    private val democracyIndexService: DemocracyIndexService,
    private val corruptionPerceptionsService: CorruptionPerceptionsService,
    private val pressFreedomService: PressFreedomService,
    private val gdpService: GDPService,
) : ViewModel() {
    fun getViewState(): Flow<ViewState> = _viewState
    private val _viewState = MutableStateFlow(
        ViewState(
            CountryDataState.Empty,
            CountryDataState.Empty,
        )
    )

    fun setCountryA(countryIso3Code: String) {
        loadDataForCountry(countryIso3Code) { dataState ->
            _viewState.value.updateCountryA(dataState)
        }
    }

    fun setCountryB(countryIso3Code: String) {
        loadDataForCountry(countryIso3Code) { dataState ->
            _viewState.value.updateCountryB(dataState)
        }
    }

    private fun loadDataForCountry(
        countryIso3Code: String,
        dataStateAcceptor: (CountryDataState) -> ViewState
    ) {
        if (countryIso3Code.isBlank()) {
            _viewState.value = dataStateAcceptor(CountryDataState.Empty)
            return
        }

        _viewState.value = dataStateAcceptor(CountryDataState.Loading)
        viewModelScope.launch(dispatchers.io) {
            try {
                val indexesData = mutableListOf<IndexDataOfCountry>()

                val populationIndexData = populationService.getLastYearData(countryIso3Code)
                indexesData.add(
                    extractFromPopulationIndex(
                        R.string.index_name_population,
                        populationIndexData,
                        POPULATION_INDEX_LAYOUT,
                    )
                )

                val democracyIndexData = democracyIndexService.getLastYearData(countryIso3Code)
                indexesData.add(
                    extractFromPopulationIndex(
                        R.string.index_name_democracy,
                        democracyIndexData,
                        DEMOCRACY_INDEX_LAYOUT,
                    )
                )

                val corruptionPerceptionsData =
                    corruptionPerceptionsService.getLastYearData(countryIso3Code)
                indexesData.add(
                    extractFromPopulationIndex(
                        R.string.index_name_corruption_perceptions,
                        corruptionPerceptionsData,
                        CORRUPTION_PERCEPTIONS_LAYOUT,
                    )
                )

                val pressFreedomData = pressFreedomService.getLastYearData(countryIso3Code)
                indexesData.add(
                    extractFromPopulationIndex(
                        R.string.index_name_press_freedom,
                        pressFreedomData,
                        PRESS_FREEDOM_INDEX_LAYOUT,
                    )
                )

                val gdpData = gdpService.getLastYearData(countryIso3Code)
                indexesData.add(
                    extractFromPopulationIndex(
                        R.string.index_name_gdp,
                        gdpData,
                        GDP_INDEX_LAYOUT,
                    )
                )

                val countryName = CountryResourceBindings.getNameIdByCode(countryIso3Code)!!
                val data = CompleteDataOfCountry(countryName, indexesData)
                _viewState.value = dataStateAcceptor(CountryDataState.Success(data))
            } catch (e: Exception) {
                _viewState.value = dataStateAcceptor(CountryDataState.Error(e))
            }
        }
    }

    private fun <IndexValue> extractFromPopulationIndex(
        indexId: Int,
        indexValue: IndexValue,
        indexLayout: IndexFeaturesLayout<IndexValue>,
    ): IndexDataOfCountry {
        val featureValues = indexLayout.features.associate { featureDescriptor ->
            featureDescriptor.featureId to
                    featureDescriptor.valueExtractor(indexValue)
        }

        return IndexDataOfCountry(indexId, featureValues)
    }

    companion object {
        class ViewState(
            val countryAState: CountryDataState,
            val countryBState: CountryDataState,
        ) {
            fun updateCountryA(newDataState: CountryDataState): ViewState {
                return ViewState(newDataState, this.countryBState)
            }

            fun updateCountryB(newDataState: CountryDataState): ViewState {
                return ViewState(this.countryAState, newDataState)
            }
        }

        sealed class CountryDataState {
            object Empty : CountryDataState()
            object Loading : CountryDataState()
            data class Success(val data: CompleteDataOfCountry) : CountryDataState()
            data class Error(val exception: Throwable) : CountryDataState()
        }

        data class CompleteDataOfCountry(
            val countryId: Int,
            val indexesData: List<IndexDataOfCountry>,
        )

        data class IndexDataOfCountry(
            val indexId: Int,
            val featuresData: Map<Int, FeatureValue>
        )
    }
}

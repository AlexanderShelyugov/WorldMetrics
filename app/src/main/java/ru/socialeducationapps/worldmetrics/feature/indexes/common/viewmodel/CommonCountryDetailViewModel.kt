package ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CommonIndexLayout
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CountryDetailViewModel.Companion.ViewState

abstract class CommonCountryDetailViewModel<Index> constructor(
    private val service: IndexFeatureService<Index>,
    private val dispatchers: DispatcherProvider,
    val indexLayout: CommonIndexLayout<Index>,
) : ViewModel(), CountryDetailViewModel<Index> {
    override fun getViewState(): Flow<ViewState<Index>> = _viewState
    override fun getColorCalculator(indexFeature: Int): ColorOfDataCalculator =
        _colorCalculator[indexFeature]!!

    protected abstract fun getFeatureRangeExtractors():
            Map<Int, (IndexFeatureService<Index>) -> FeatureMedianRange>


    private var country: String = ""
    private val _viewState = MutableStateFlow<ViewState<Index>>(ViewState.Initial())
    private var _colorCalculator: Map<Int, ColorOfDataCalculator>

    init {
        runBlocking {
            val featureRangeExtractors = getFeatureRangeExtractors()
            indexLayout.features.asSequence()
                .map { feature -> feature.first }
                .map { featureName -> featureName to featureRangeExtractors[featureName] }
                .map { (featureName, rangeExtractor) -> featureName to rangeExtractor!!(service) }
                .map { (featureName, featureRange) ->
                    featureName to ColorOfDataCalculator(
                        featureRange.first, featureRange.second, featureRange.third
                    )
                }
                .toMap()
                .also { _colorCalculator = it }
        }
    }

    final override fun setCountry(country: String) {
        this.country = country
        reloadViewState()
    }

    override fun onOpen() {
        when (_viewState.value) {
            is ViewState.Initial, is ViewState.Error -> reloadViewState()
            else -> return
        }
    }

    private fun reloadViewState() {
        viewModelScope.launch(dispatchers.io) {
            _viewState.value = ViewState.Loading()
            try {
                val data = service.getAllData(country)
                val lastYearData: Index = service.getLastYearData(country)
                _viewState.value = ViewState.Success(lastYearData, data)
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e)
            }
        }
    }
}
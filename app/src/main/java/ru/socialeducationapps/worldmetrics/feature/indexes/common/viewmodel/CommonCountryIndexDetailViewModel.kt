package ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.IndexFeaturesLayout
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CountryIndexDetailViewModel.Companion.ViewState
import java.lang.Integer.min

abstract class CommonCountryIndexDetailViewModel<IndexType> constructor(
    private val service: IndexFeatureService<IndexType>,
    private val dispatchers: DispatcherProvider,
    val indexLayout: IndexFeaturesLayout<IndexType>,
) : ViewModel(), CountryIndexDetailViewModel<IndexType> {
    override fun getViewState(): Flow<ViewState<IndexType>> = _viewState
    override fun getColorCalculatorsForFeatures(): Map<Int, ColorOfDataCalculator> =
        _colorCalculator

    protected abstract fun getFeatureRangeExtractors():
            Map<Int, (IndexFeatureService<IndexType>) -> FeatureMedianRange>


    private var country: String = ""
    private val _viewState = MutableStateFlow<ViewState<IndexType>>(ViewState.Initial())
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
                val data = service.getAllData(country).let { allData ->
                    val itemsCount = getDataItemsMaxCount()?.let { min(it, allData.size) }
                        ?: allData.size
                    allData.takeLast(itemsCount).reversed()
                }
                val lastYearData: IndexType = service.getLastYearData(country)
                _viewState.value = ViewState.Success(lastYearData, data)
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e)
            }
        }
    }

    private fun getDataItemsMaxCount(): Int {
        // TODO move to some configuration property
        return 20;
    }
}
package ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexOverviewService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CountryFeatureValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.LOADING_ERROR
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.LOADING_SUCCESS
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.NOT_INITIALIZED
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.initialState
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.loadingState

private typealias ViewState = OverviewViewModel.Companion.ViewState

abstract class CommonOverviewViewModel constructor(
    private val service: IndexOverviewService,
    private val dispatchers: DispatcherProvider,
) : ViewModel(), OverviewViewModel {
    override fun getLastYearData(): Flow<ViewState> = _lastYearData
    override fun getColorCalculator(): ColorOfDataCalculator = _colorCalculator

    private val _lastYearData = MutableStateFlow(initialState())
    private var _colorCalculator: ColorOfDataCalculator

    init {
        runBlocking {
            val indexMinMedianMax = service.getMinMedianMaxForAllCountries()
            _colorCalculator = ColorOfDataCalculator(
                indexMinMedianMax.first,
                indexMinMedianMax.second,
                indexMinMedianMax.third,
            )
        }
    }

    fun onOpen() {
        when (_lastYearData.value.status) {
            NOT_INITIALIZED, LOADING_ERROR -> reloadLastYearData()
            else -> return
        }
    }

    private fun reloadLastYearData() {
        viewModelScope.launch(dispatchers.io) {
            _lastYearData.value = loadingState()
            val data: List<CountryFeatureValue>
            try {
                data = service.getLastYearData()
                _lastYearData.value = ViewState(LOADING_SUCCESS, data)
            } catch (e: Exception) {
                _lastYearData.value = ViewState(LOADING_ERROR, emptyList())
            }
        }
    }
}
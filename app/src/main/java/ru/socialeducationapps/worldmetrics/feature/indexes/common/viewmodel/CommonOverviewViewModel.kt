package ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexOverviewService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.SimpleCountryValue

abstract class CommonOverviewViewModel constructor(
    private val service: IndexOverviewService,
    private val dispatchers: DispatcherProvider,
) : ViewModel(), OverviewViewModel {
    private val _lastYearData = MutableStateFlow<List<SimpleCountryValue>?>(null)
    final override val lastYearData: Flow<List<SimpleCountryValue>?>
        get() = _lastYearData
            .also { reloadLastYearData() }
            .asStateFlow()

    private fun reloadLastYearData() {
        viewModelScope.launch(dispatchers.io) {
            _lastYearData.value = service.getLastYearData()
        }
    }
}
package ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexOverviewService

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
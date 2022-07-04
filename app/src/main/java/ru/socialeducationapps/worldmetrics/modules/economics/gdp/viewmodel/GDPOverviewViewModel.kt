package ru.socialeducationapps.worldmetrics.modules.economics.gdp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import javax.inject.Inject

@HiltViewModel
class GDPOverviewViewModel @Inject constructor(
    private val service: GDPService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    private val _lastYearData = MutableStateFlow<List<SimpleCountryValue>>(emptyList())
    val lastYearData: Flow<List<SimpleCountryValue>>
        get() = _lastYearData
            .also { loadLastYearData() }
            .asStateFlow()

    private fun loadLastYearData() {
        viewModelScope.launch(dispatchers.io) {
            _lastYearData.value = service.getLastYearData()
        }
    }

    fun getValueRange() = runBlocking { service.getValueUsdPerCapitaRange() }
}
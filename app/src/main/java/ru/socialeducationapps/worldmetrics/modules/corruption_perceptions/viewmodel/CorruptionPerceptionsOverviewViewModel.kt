package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsOverviewViewModel @Inject constructor(
    private val service: CorruptionPerceptionsService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    private val _lastYearData = MutableStateFlow<List<SimpleCountryValue>>(emptyList())
    val lastYearData: Flow<List<SimpleCountryValue>>
        get() = _lastYearData
            .also { triggerReloadLastYear() }
            .asStateFlow()

    private fun triggerReloadLastYear() {
        viewModelScope.launch(dispatchers.io) {
            val res = service.getLastYearData()
            _lastYearData.value = res
        }
    }

    fun getValueRange(): FeatureRange = runBlocking {
        service.getValueRange()
    }
}
package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _lastYearData = MutableLiveData<List<SimpleCountryValue>>()
    val lastYearData: LiveData<List<SimpleCountryValue>>
        get() = triggerReloadLastYear()

    private fun triggerReloadLastYear(): LiveData<List<SimpleCountryValue>> {
        viewModelScope.launch(dispatchers.io) {
            val res = service.getLastYearData()
            _lastYearData.value = res
        }
        return _lastYearData
    }

    fun getValueRange(): FeatureRange = runBlocking {
        service.getValueRange()
    }
}
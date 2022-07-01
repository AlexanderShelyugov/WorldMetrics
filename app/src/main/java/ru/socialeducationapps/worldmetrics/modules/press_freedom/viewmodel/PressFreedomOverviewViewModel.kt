package ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomOverviewViewModel @Inject constructor(
    private val service: PressFreedomService,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {
    private val _lastYearData = MutableLiveData<List<SimpleCountryValue>>()
    val lastYearData: LiveData<List<SimpleCountryValue>>
        get() = _lastYearData
            .also { loadLastYearData() }

    private fun loadLastYearData() {
        viewModelScope.launch(dispatchers.io) {
            _lastYearData.value = service.getLastYearData()
        }
    }

    fun getValueRange() = service.getValueRange()
}
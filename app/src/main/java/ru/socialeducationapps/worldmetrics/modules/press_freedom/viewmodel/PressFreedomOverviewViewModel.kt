package ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomOverviewViewModel @Inject constructor(
    private val service: PressFreedomService
) : ViewModel() {

    private val lastYearDataContainer = MutableLiveData<List<SimpleCountryValue>>().also {
        it.value = service.getLastYearData()
    }

    val lastYearData: LiveData<List<SimpleCountryValue>> by lazy {
        lastYearDataContainer
    }

    fun getValueRange() = service.getValueRange()
}
package ru.alexander.worldmetrics.modules.press_freedom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomOverviewViewModel @Inject constructor(
    private val service: PressFreedomService
) : ViewModel() {

    private val lastYearDataContainer = MutableLiveData<Map<String, String>>().also {
        it.value = service.getLastYearData()
    }

    val lastYearData: LiveData<Map<String, String>> by lazy {
        lastYearDataContainer
    }

    fun getValueRange(): Pair<Float, Float> = service.getValueRange()
}
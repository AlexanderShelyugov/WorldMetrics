package ru.alexander.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsOverviewViewModel @Inject constructor(
    private val service: CorruptionPerceptionsService
) : ViewModel() {

    private val lastYearDataContainer = MutableLiveData<Map<String, String>>().also {
        it.value = service.getLastYearData()
    }

    val lastYearData: LiveData<Map<String, String>> by lazy {
        lastYearDataContainer
    }

    fun getValueRange() = service.getValueRange()
}
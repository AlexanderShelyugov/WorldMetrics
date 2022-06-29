package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsOverviewViewModel @Inject constructor(
    private val service: CorruptionPerceptionsService
) : ViewModel() {

    private val lastYearDataContainer = MutableLiveData<List<SimpleCountryValue>>().also {
        it.value = service.getLastYearData()
    }

    val lastYearData: LiveData<List<SimpleCountryValue>> by lazy {
        lastYearDataContainer
    }

    fun getValueRange() = service.getValueRange()
}
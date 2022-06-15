package ru.alexander.worldmetrics.modules.corruption_perceptions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsCountryDetailViewModel @Inject constructor(
    private val service: CorruptionPerceptionsService
) : ViewModel() {
    private var country: String = ""
    private val allDataContainer = MutableLiveData<List<CorruptionPerceptionsValue>>()

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val allData: LiveData<List<CorruptionPerceptionsValue>> by lazy {
        allDataContainer
    }

    private fun loadData() {
        allDataContainer.value = service.getAllData(country)
    }
}
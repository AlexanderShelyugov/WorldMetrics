package ru.alexander.worldmetrics.modules.corruption_perceptions.viewmodel

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

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val allData: MutableLiveData<List<CorruptionPerceptionsValue>> by lazy {
        MutableLiveData<List<CorruptionPerceptionsValue>>()
    }

    private fun loadData() {
        allData.value = service.getAllData(country)
    }
}
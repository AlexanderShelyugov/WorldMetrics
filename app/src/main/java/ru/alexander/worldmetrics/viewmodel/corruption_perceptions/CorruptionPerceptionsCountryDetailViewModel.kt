package ru.alexander.worldmetrics.viewmodel.corruption_perceptions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.model.corruption_perceptions.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.service.api.CorruptionPerceptionsService
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
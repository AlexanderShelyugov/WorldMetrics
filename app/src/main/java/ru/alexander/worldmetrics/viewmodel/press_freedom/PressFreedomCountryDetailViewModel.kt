package ru.alexander.worldmetrics.viewmodel.press_freedom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.model.press_freedom.PressFreedomValue
import ru.alexander.worldmetrics.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomCountryDetailViewModel @Inject constructor(
    private val service: PressFreedomService
) : ViewModel() {

    private var country: String = ""

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val lastYearData: MutableLiveData<Map<String, String>> by lazy {
        MutableLiveData<Map<String, String>>().also {
            it.value = service.getLastYearData()
        }
    }

    val allData: MutableLiveData<List<PressFreedomValue>> by lazy {
        MutableLiveData<List<PressFreedomValue>>()
    }

    private fun loadData() {
        lastYearData.value = service.getLastYearData()
        allData.value = service.getData(country)
    }
}
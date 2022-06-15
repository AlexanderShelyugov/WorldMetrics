package ru.alexander.worldmetrics.modules.press_freedom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.alexander.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomCountryDetailViewModel @Inject constructor(
    private val service: PressFreedomService
) : ViewModel() {

    private var country: String = ""
    private val lastYearDataContainer = MutableLiveData<Map<String, String>>().also {
        it.value = service.getLastYearData()
    }
    private val allDataContainer = MutableLiveData<List<PressFreedomValue>>()

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val lastYearData: LiveData<Map<String, String>> by lazy {
        lastYearDataContainer
    }

    val allData: LiveData<List<PressFreedomValue>> by lazy {
        allDataContainer
    }

    private fun loadData() {
        lastYearDataContainer.value = service.getLastYearData()
        allDataContainer.value = service.getData(country)
    }
}
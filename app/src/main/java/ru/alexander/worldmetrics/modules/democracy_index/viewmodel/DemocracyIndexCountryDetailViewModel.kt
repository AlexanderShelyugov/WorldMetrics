package ru.alexander.worldmetrics.modules.democracy_index.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.modules.democracy_index.model.DemocracyIndexValue
import ru.alexander.worldmetrics.modules.democracy_index.service.api.DemocracyIndexService
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexCountryDetailViewModel @Inject constructor(
    private val service: DemocracyIndexService
) : ViewModel() {
    private var country: String = ""
    private val lastYearDataContainer = MutableLiveData<DemocracyIndexValue>()
    private val allDataContainer = MutableLiveData<List<DemocracyIndexValue>>()

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val lastYearData: LiveData<DemocracyIndexValue> by lazy {
        lastYearDataContainer
    }

    val allData: LiveData<List<DemocracyIndexValue>> by lazy {
        allDataContainer
    }

    private fun loadData() {
        lastYearDataContainer.value = service.getLastYearData(country)
        allDataContainer.value = service.getAllYearData(country)
    }
}
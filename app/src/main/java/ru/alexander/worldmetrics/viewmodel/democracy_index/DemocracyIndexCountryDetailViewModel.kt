package ru.alexander.worldmetrics.viewmodel.democracy_index

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.model.DemocracyIndexValue
import ru.alexander.worldmetrics.service.api.DemocracyIndexService
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexCountryDetailViewModel @Inject constructor(
    private val service: DemocracyIndexService
) : ViewModel() {
    private var country: String = ""

    fun setCountry(country: String) {
        this.country = country
        loadData()
    }

    val lastYearData: MutableLiveData<DemocracyIndexValue> by lazy {
        MutableLiveData<DemocracyIndexValue>()
    }

    private fun loadData() {
        lastYearData.value = service.getLastYearData(country)
    }
}
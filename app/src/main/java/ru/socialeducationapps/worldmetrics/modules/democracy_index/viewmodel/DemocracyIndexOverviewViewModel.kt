package ru.socialeducationapps.worldmetrics.modules.democracy_index.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.modules.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexOverviewViewModel @Inject constructor(
    private val service: DemocracyIndexService
) : ViewModel() {
    private val lastYearDataContainer = MutableLiveData<List<SimpleCountryValue>>().also {
        it.value = service.getLastYearData()
    }

    val lastYearData: LiveData<List<SimpleCountryValue>> by lazy {
        lastYearDataContainer
    }

    fun getValueRange() = service.getValueRange()
}

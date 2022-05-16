package ru.alexander.worldmetrics.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.service.api.DemocracyIndexService
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexOverviewViewModel @Inject constructor(
    private val service: DemocracyIndexService
) : ViewModel() {
    val lastYearData: MutableLiveData<Map<String, String>> by lazy {
        MutableLiveData<Map<String, String>>().also {
            it.value = service.getLastYearData()
        }
    }
}

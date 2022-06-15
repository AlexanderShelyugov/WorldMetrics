package ru.alexander.worldmetrics.democracy_index.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexander.worldmetrics.democracy_index.service.api.DemocracyIndexService
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexOverviewViewModel @Inject constructor(
    private val service: DemocracyIndexService
) : ViewModel() {
    val lastYearData: LiveData<Map<String, String>> by lazy {
        MutableLiveData<Map<String, String>>().also {
            it.value = service.getLastYearData()
        }
    }

    fun getValueRange(): Pair<Float, Float> = service.getValueRange()
}

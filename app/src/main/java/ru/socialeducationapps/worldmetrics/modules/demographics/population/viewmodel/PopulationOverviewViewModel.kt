package ru.socialeducationapps.worldmetrics.modules.demographics.population.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class PopulationOverviewViewModel @Inject constructor(
    private val service: PopulationService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers) {
    override fun getValueRange() = getTotalPopulationRange()
    fun getTotalPopulationRange() = runBlocking { service.getTotalPopulationRange() }
    fun getFemalePopulationRange() = runBlocking { service.getFemalePopulationRange() }
    fun getMalePopulationRange() = runBlocking { service.getMalePopulationRange() }
    fun getPopulationDensityRange() = runBlocking { service.getPopulationDensityRange() }
}
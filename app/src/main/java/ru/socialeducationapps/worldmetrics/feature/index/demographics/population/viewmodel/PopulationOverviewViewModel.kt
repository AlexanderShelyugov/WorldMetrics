package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class PopulationOverviewViewModel @Inject constructor(
    private val service: PopulationService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers) {
    override fun getValueRange() = runBlocking { service.getTotalPopulationRange() }
}
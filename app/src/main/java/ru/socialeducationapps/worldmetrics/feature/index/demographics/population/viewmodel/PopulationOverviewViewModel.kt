package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class PopulationOverviewViewModel @Inject constructor(
    service: PopulationService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers)
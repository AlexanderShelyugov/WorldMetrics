package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexOverviewViewModel @Inject constructor(
    service: DemocracyIndexService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers)

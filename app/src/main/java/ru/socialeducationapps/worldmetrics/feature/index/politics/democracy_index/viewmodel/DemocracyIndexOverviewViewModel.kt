package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexOverviewViewModel @Inject constructor(
    private val service: DemocracyIndexService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers) {
    override fun getValueRange() = runBlocking { service.getValueRange() }
}

package ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.service.api.DemocracyIndexService
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexOverviewViewModel @Inject constructor(
    private val service: DemocracyIndexService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel<DemocracyIndexValue>(service, dispatchers) {
    override fun getValueRange() = runBlocking { service.getValueRange() }
}

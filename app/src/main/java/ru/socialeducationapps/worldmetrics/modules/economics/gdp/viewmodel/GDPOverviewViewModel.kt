package ru.socialeducationapps.worldmetrics.modules.economics.gdp.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class GDPOverviewViewModel @Inject constructor(
    private val service: GDPService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers) {
    override fun getValueRange() = runBlocking { service.getValueUsdPerCapitaRange() }
}
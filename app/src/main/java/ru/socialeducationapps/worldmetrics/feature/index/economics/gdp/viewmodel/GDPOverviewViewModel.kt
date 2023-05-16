package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class GDPOverviewViewModel @Inject constructor(
    private val service: GDPService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers) {
    override fun getValueRange() = runBlocking { service.getValueUsdPerCapitaRange() }
}
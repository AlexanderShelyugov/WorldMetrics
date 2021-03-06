package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.service.api.PressFreedomService
import javax.inject.Inject

@HiltViewModel
class PressFreedomOverviewViewModel @Inject constructor(
    private val service: PressFreedomService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers) {
    override fun getValueRange() = service.getValueRange()
}
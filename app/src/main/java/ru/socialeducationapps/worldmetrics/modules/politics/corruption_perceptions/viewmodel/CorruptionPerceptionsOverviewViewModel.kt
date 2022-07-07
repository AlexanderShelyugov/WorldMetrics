package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsOverviewViewModel @Inject constructor(
    private val service: CorruptionPerceptionsService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers) {
    override fun getValueRange(): FeatureRange = runBlocking { service.getValueRange() }
}
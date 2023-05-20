package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsOverviewViewModel @Inject constructor(
    service: CorruptionPerceptionsService,
    dispatchers: DispatcherProvider,
) : CommonOverviewViewModel(service, dispatchers)
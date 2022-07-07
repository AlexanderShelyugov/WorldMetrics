package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.model.CorruptionPerceptionsData.Companion.CORRUPTION_PERCEPTIONS_LAYOUT
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsCountryDetailViewModel @Inject constructor(
    service: CorruptionPerceptionsService,
    dispatchers: DispatcherProvider,
) : CommonCountryDetailViewModel<CorruptionPerceptionsValue>(
    service, dispatchers, CORRUPTION_PERCEPTIONS_LAYOUT
) {
    override fun getFeatureExtractors(): Map<Int, (IndexFeatureService<CorruptionPerceptionsValue>) -> FeatureRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<CorruptionPerceptionsValue>) -> FeatureRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (CorruptionPerceptionsService) -> FeatureRange>(
            R.string.index_name_corruption_perceptions to { runBlocking { it.getValueRange() } }
        )
    }
}
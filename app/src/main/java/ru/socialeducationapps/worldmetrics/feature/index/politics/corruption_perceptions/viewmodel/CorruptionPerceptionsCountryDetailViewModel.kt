package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsData.Companion.CORRUPTION_PERCEPTIONS_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryDetailViewModel
import javax.inject.Inject

@HiltViewModel
class CorruptionPerceptionsCountryDetailViewModel @Inject constructor(
    service: CorruptionPerceptionsService,
    dispatchers: DispatcherProvider,
) : CommonCountryDetailViewModel<CorruptionPerceptionsValue>(
    service, dispatchers, CORRUPTION_PERCEPTIONS_LAYOUT
) {
    override fun getFeatureRangeExtractors(): Map<Int, (IndexFeatureService<CorruptionPerceptionsValue>) -> FeatureMedianRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<CorruptionPerceptionsValue>) -> FeatureMedianRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (CorruptionPerceptionsService) -> FeatureMedianRange>(
            R.string.index_name_corruption_perceptions to { runBlocking { it.getValueRange() } }
        )
    }
}
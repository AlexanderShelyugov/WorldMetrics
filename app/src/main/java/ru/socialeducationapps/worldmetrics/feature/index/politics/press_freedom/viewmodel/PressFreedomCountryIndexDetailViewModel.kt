package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomData.Companion.PRESS_FREEDOM_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryIndexDetailViewModel
import javax.inject.Inject

@HiltViewModel
class PressFreedomCountryIndexDetailViewModel @Inject constructor(
    service: PressFreedomService,
    dispatchers: DispatcherProvider,
) : CommonCountryIndexDetailViewModel<PressFreedomValue>(
    service, dispatchers, PRESS_FREEDOM_INDEX_LAYOUT
) {
    override fun getFeatureRangeExtractors(): Map<Int, (IndexFeatureService<PressFreedomValue>) -> FeatureMedianRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<PressFreedomValue>) -> FeatureMedianRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (PressFreedomService) -> FeatureMedianRange>(
            R.string.index_name_press_freedom to { it.getValueRange() },
            R.string.press_freedom_political_context to { it.getPCRange() },
            R.string.press_freedom_economic_context to { it.getECRange() },
            R.string.press_freedom_legal_context to { it.getLCRange() },
            R.string.press_freedom_social_context to { it.getSCRange() },
            R.string.press_freedom_safety to { it.getSRange() },
        )
    }
}

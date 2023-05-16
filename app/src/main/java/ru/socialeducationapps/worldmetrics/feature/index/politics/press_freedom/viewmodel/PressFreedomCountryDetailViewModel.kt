package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomData.Companion.PRESS_FREEDOM_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryDetailViewModel
import javax.inject.Inject

@HiltViewModel
class PressFreedomCountryDetailViewModel @Inject constructor(
    service: PressFreedomService,
    dispatchers: DispatcherProvider,
) : CommonCountryDetailViewModel<PressFreedomValue>(
    service, dispatchers, PRESS_FREEDOM_INDEX_LAYOUT
) {
    override fun getFeatureExtractors(): Map<Int, (IndexFeatureService<PressFreedomValue>) -> FeatureRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<PressFreedomValue>) -> FeatureRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (PressFreedomService) -> FeatureRange>(
            R.string.index_name_press_freedom to { it.getValueRange() },
            R.string.press_freedom_political_context to { it.getPCRange() },
            R.string.press_freedom_economic_context to { it.getECRange() },
            R.string.press_freedom_legal_context to { it.getLCRange() },
            R.string.press_freedom_social_context to { it.getSCRange() },
            R.string.press_freedom_safety to { it.getSRange() },
        )
    }
}

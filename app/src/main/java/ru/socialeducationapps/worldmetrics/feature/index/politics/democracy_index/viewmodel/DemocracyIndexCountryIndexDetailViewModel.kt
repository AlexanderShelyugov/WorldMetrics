package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexData.Companion.DEMOCRACY_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryIndexDetailViewModel
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexCountryIndexDetailViewModel @Inject constructor(
    service: DemocracyIndexService,
    dispatchers: DispatcherProvider
) : CommonCountryIndexDetailViewModel<DemocracyIndexValue>(
    service, dispatchers, DEMOCRACY_INDEX_LAYOUT
) {
    override fun getFeatureRangeExtractors(): Map<Int, (IndexFeatureService<DemocracyIndexValue>) -> FeatureMedianRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<DemocracyIndexValue>) -> FeatureMedianRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (DemocracyIndexService) -> FeatureMedianRange>(
            R.string.index_name_democracy to { it.getValueRange() },
            R.string.electoral_process_and_pluralism to { it.getEPAPRange() },
            R.string.functioning_of_government to { it.getFOGRange() },
            R.string.political_participation to { it.getPPRange() },
            R.string.political_culture to { it.getPCRange() },
            R.string.civil_liberties to { it.getCLRange() },
        )
    }
}
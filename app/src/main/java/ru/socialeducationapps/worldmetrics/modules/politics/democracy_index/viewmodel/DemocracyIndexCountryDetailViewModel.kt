package ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexData.Companion.DEMOCRACY_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.service.api.DemocracyIndexService
import javax.inject.Inject

@HiltViewModel
class DemocracyIndexCountryDetailViewModel @Inject constructor(
    service: DemocracyIndexService,
    dispatchers: DispatcherProvider
) : CommonCountryDetailViewModel<DemocracyIndexValue>(
    service, dispatchers, DEMOCRACY_INDEX_LAYOUT
) {
    override fun getFeatureExtractors(): Map<Int, (IndexFeatureService<DemocracyIndexValue>) -> FeatureRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<DemocracyIndexValue>) -> FeatureRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (DemocracyIndexService) -> FeatureRange>(
            R.string.index_name_democracy to { it.getValueRange() },
            R.string.electoral_process_and_pluralism to { it.getEPAPRange() },
            R.string.functioning_of_government to { it.getFOGRange() },
            R.string.political_participation to { it.getPPRange() },
            R.string.political_culture to { it.getPCRange() },
            R.string.civil_liberties to { it.getCLRange() },
        )
    }
}
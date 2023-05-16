package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPData.Companion.GDP_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryDetailViewModel
import javax.inject.Inject

@HiltViewModel
class GDPCountryDetailViewModel @Inject constructor(
    service: GDPService,
    dispatchers: DispatcherProvider,
) : CommonCountryDetailViewModel<GDPValue>(service, dispatchers, GDP_INDEX_LAYOUT) {
    override fun getFeatureExtractors(): Map<Int, (IndexFeatureService<GDPValue>) -> FeatureRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<GDPValue>) -> FeatureRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (GDPService) -> FeatureRange>(
            R.string.gdp_millions_usd to { it.getValueMlnUsdRange() },
            R.string.gdp_usd_per_capita to { it.getValueUsdPerCapitaRange() }
        )
    }
}
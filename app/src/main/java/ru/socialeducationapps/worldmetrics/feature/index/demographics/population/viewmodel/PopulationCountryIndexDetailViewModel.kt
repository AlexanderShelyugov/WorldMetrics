package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexData.Companion.POPULATION_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryIndexDetailViewModel
import javax.inject.Inject

@HiltViewModel
class PopulationCountryIndexDetailViewModel @Inject constructor(
    service: PopulationService,
    dispatchers: DispatcherProvider,
) : CommonCountryIndexDetailViewModel<PopulationIndexValue>(
    service, dispatchers, POPULATION_INDEX_LAYOUT
) {
    override fun getFeatureRangeExtractors(): Map<Int, (IndexFeatureService<PopulationIndexValue>) -> FeatureMedianRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<PopulationIndexValue>) -> FeatureMedianRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (PopulationService) -> FeatureMedianRange>(
            R.string.population_population_total to { runBlocking { it.getTotalPopulationRange() } },
            R.string.population_population_female to { runBlocking { it.getFemalePopulationRange() } },
            R.string.population_population_male to { runBlocking { it.getMalePopulationRange() } },
            R.string.population_population_density to { runBlocking { it.getPopulationDensityRange() } },
        )
    }
}
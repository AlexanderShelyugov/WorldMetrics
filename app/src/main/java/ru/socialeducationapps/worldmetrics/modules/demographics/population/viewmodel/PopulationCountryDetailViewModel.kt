package ru.socialeducationapps.worldmetrics.modules.demographics.population.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexData.Companion.POPULATION_INDEX_LAYOUT
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.modules.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.service.api.IndexFeatureService
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonCountryDetailViewModel
import javax.inject.Inject

@HiltViewModel
class PopulationCountryDetailViewModel @Inject constructor(
    service: PopulationService,
    dispatchers: DispatcherProvider,
) : CommonCountryDetailViewModel<PopulationIndexValue>(
    service, dispatchers, POPULATION_INDEX_LAYOUT
) {
    override fun getFeatureExtractors(): Map<Int, (IndexFeatureService<PopulationIndexValue>) -> FeatureRange> =
        FEATURE_RANGE_EXTRACTORS as Map<Int, (IndexFeatureService<PopulationIndexValue>) -> FeatureRange>

    private companion object {
        val FEATURE_RANGE_EXTRACTORS = mapOf<Int, (PopulationService) -> FeatureRange>(
            R.string.population_population_total to { runBlocking { it.getTotalPopulationRange() } },
            R.string.population_population_female to { runBlocking { it.getFemalePopulationRange() } },
            R.string.population_population_male to { runBlocking { it.getMalePopulationRange() } },
            R.string.population_population_density to { runBlocking { it.getPopulationDensityRange() } },
        )
    }
}
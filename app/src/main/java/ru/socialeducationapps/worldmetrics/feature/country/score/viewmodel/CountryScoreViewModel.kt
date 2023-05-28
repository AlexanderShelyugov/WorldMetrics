package ru.socialeducationapps.worldmetrics.feature.country.score.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.AllIndexes
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import javax.inject.Inject

@HiltViewModel
class CountryScoreViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val populationService: PopulationService,
    private val democracyIndexService: DemocracyIndexService,
    private val corruptionPerceptionsService: CorruptionPerceptionsService,
    private val pressFreedomService: PressFreedomService,
    private val gdpService: GDPService,
) : ViewModel() {

    companion object {
        private const val SCORE_TOP = 10
    }

    private val scoreCalculators = mapOf(
        R.string.index_name_population to this::calculatePopulationScore,
        R.string.index_name_gdp to this::calculateGDPScore,
        R.string.index_name_democracy to this::calculateDemocracyScore,
    )

    private val ranges = runBlocking {
        mapOf(
            R.string.index_name_democracy to democracyIndexService.getValueRange(),
            R.string.index_name_corruption_perceptions to corruptionPerceptionsService.getValueRange(),
            R.string.index_name_press_freedom to pressFreedomService.getValueRange(),
            R.string.index_name_gdp to gdpService.getValueMlnUsdRange()
        )
    }

    fun getCountryScores(country: String): Map<Int, Int> {
        return AllIndexes.ALL_INDEXES.flatMap { it.indexes }.map { index ->
            val scoreCalculator = scoreCalculators[index.name]
            scoreCalculator?.let { index.name to it(country) }
        }
            .filterNotNull()
            .toMap()
    }

    private fun calculatePopulationScore(country: String): Int {
        return calculateScore(
            { runBlocking(dispatchers.io) { populationService.getLastYearData(country) }.populationDensity },
            { runBlocking(dispatchers.io) { populationService.getPopulationDensityRange() } }
        )
    }

    private fun calculateGDPScore(country: String): Int {
        return calculateScore(
            { runBlocking(dispatchers.io) { gdpService.getLastYearData(country) }.valueUsdCap },
            { gdpService.getValueUsdPerCapitaRange() }
        )
    }

    private fun calculateDemocracyScore(country: String): Int {
        return calculateScore(
            { runBlocking(dispatchers.io) { democracyIndexService.getLastYearData(country) }.democracyIndex },
            { democracyIndexService.getValueRange() }
        )
    }

    private fun calculateScore(
        indexExtractor: () -> Float, rangeExtractor: () -> FeatureMedianRange
    ): Int {
        val range = rangeExtractor.invoke()
        return scoreWithinRange(indexExtractor.invoke(), range.first, range.third)
    }

    private fun scoreWithinRange(x: Float, min: Float, max: Float): Int {
        val ratio = (x - min) / (max - min) // 0..1
        return (ratio * SCORE_TOP).toInt()
    }
}
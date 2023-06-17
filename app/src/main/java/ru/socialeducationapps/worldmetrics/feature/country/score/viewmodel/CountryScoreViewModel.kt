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
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.AllIndexes.Companion.ALL_INDEXES
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureMedianRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureValue
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
        R.string.index_group_name_demographic to this::calculateDemographicScore,
        R.string.index_group_name_economic to this::calculateEconomicalScore,
        R.string.index_group_name_political to this::calculatePoliticalScore,
    )

    fun getCountryScores(country: String): Map<Int, Int> {
        return ALL_INDEXES.map { it.name }
            .mapNotNull { indexGroupId ->
                val scoreCalculator = scoreCalculators[indexGroupId]
                scoreCalculator?.let { indexGroupId to it(country) }
            }
            .toMap()
    }

    private fun calculateDemographicScore(country: String): Int {
        val valuesAndRanges = runBlocking(dispatchers.io) {
            val lastYearData = populationService.getLastYearData(country)
            listOf(
                lastYearData.populationTotal to populationService.getTotalPopulationRange(),
                lastYearData.populationMale to populationService.getMalePopulationRange(),
                lastYearData.populationFemale to populationService.getFemalePopulationRange(),
                lastYearData.populationDensity to populationService.getPopulationDensityRange(),
            )
        }

        return calculateScore(valuesAndRanges)
    }

    private fun calculateEconomicalScore(country: String): Int {
        val valuesAndRanges = runBlocking(dispatchers.io) {
            val lastYearData = gdpService.getLastYearData(country)
            listOf(
                lastYearData.valueMlnUsd to gdpService.getValueMlnUsdRange(),
                lastYearData.valueUsdCap to gdpService.getValueUsdPerCapitaRange(),
            )
        }

        return calculateScore(valuesAndRanges)
    }

    private fun calculatePoliticalScore(country: String): Int {
        val valuesAndRanges = runBlocking(dispatchers.io) {
            val demLastYearData = democracyIndexService.getLastYearData(country)
            val demValues = listOf(
                demLastYearData.democracyIndex to democracyIndexService.getValueRange(),
                demLastYearData.civilLiberties to democracyIndexService.getCLRange(),
                demLastYearData.politicalCulture to democracyIndexService.getPCRange(),
                demLastYearData.electoralProcessAndPluralism to democracyIndexService.getEPAPRange(),
                demLastYearData.functioningOfGovernment to democracyIndexService.getFOGRange(),
                demLastYearData.politicalParticipation to democracyIndexService.getPPRange(),
            )

            val corrupLYD = corruptionPerceptionsService.getLastYearData(country)
            val corrupValues = listOf(
                corrupLYD.value to corruptionPerceptionsService.getValueRange()
            )

            val pressLYD = pressFreedomService.getLastYearData(country)
            val pressValues = listOf(
                pressLYD.score to pressFreedomService.getValueRange(),
                pressLYD.politicalContext to pressFreedomService.getPCRange(),
                pressLYD.economicContext to pressFreedomService.getECRange(),
                pressLYD.legalContext to pressFreedomService.getLCRange(),
                pressLYD.socialContext to pressFreedomService.getSCRange(),
                pressLYD.safety to pressFreedomService.getSRange(),
            )

            listOf(demValues, corrupValues, pressValues).flatten()
        }

        return calculateScore(valuesAndRanges)
    }

    private fun calculateScore(
        valuesAndRanges: List<Pair<FeatureValue, FeatureMedianRange>>
    ): Int {
        val averageRatio = valuesAndRanges
            .filter { !it.first.isNaN() && it.first >= 0 }
            .map { ratio(it.first, it.second.first, it.second.third) }
            .average()
            .toFloat()
            .takeIf { it.isFinite() } ?: 0f
        return (averageRatio * SCORE_TOP).toInt()
    }

    private fun ratio(x: Float, min: Float, max: Float): Float {
        require(x in min..max)
        return (x - min) / (max - min) // 0..1
    }
}
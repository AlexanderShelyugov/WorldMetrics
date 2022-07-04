package ru.socialeducationapps.worldmetrics.modules.demographics.population.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureExtractor

private typealias Index = PopulationIndexValue
private typealias ValueFunction = (Index) -> Float

class PopulationIndexData private constructor() {
    companion object {
        val YEAR_FUNCTION: FeatureExtractor<Index> = { it.year.toFloat() }

        val FEATURES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.population_population_total to { it.populationTotal },
            R.string.population_population_male to { it.populationMale },
            R.string.population_population_female to { it.populationFemale },
            R.string.population_population_density to { it.populationDensity },
        )

        val FEATURES_NUMBER = FEATURES_TO_SHOW.size
    }
}
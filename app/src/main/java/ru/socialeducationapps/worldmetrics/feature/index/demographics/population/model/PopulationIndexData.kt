package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CommonIndexLayout

class PopulationIndexData private constructor() {
    companion object {
        val POPULATION_INDEX_LAYOUT = CommonIndexLayout<PopulationIndexValue>(
            { it.year.toFloat() },
            listOf(
                R.string.population_population_total to { it.populationTotal },
                R.string.population_population_male to { it.populationMale },
                R.string.population_population_female to { it.populationFemale },
                R.string.population_population_density to { it.populationDensity },
            )
        )
    }
}
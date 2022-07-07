package ru.socialeducationapps.worldmetrics.modules.demographics.population.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.indexes.model.CommonIndexLayout

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
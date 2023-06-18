package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.socialeducationapps.worldmetrics.feature.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.api.PopulationService
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.service.impl.PopulationCsvService

@Module
@InstallIn(ViewModelComponent::class)
class PopulationModule {
    @Provides
    fun populationService(csvService: CsvService): PopulationService =
        PopulationCsvService(csvService)
            .apply { init(FILE_PATH) }

    companion object {
        private const val FILE_PATH = "indexes/Population.csv"
    }
}
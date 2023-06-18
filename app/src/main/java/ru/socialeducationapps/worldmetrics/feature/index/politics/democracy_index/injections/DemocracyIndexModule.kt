package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.socialeducationapps.worldmetrics.feature.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.service.impl.DemocracyIndexServiceImpl

@Module
@InstallIn(ViewModelComponent::class)
class DemocracyIndexModule {
    @Provides
    fun democracyIndexService(csvService: CsvService): DemocracyIndexService =
        DemocracyIndexServiceImpl(csvService)
            .apply { init(FILE_PATH) }

    companion object {
        private const val FILE_PATH = "indexes/DemocracyIndex.csv"
    }
}
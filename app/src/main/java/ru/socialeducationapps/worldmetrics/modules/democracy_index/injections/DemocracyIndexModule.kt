package ru.socialeducationapps.worldmetrics.modules.democracy_index.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.socialeducationapps.worldmetrics.modules.democracy_index.service.api.DemocracyIndexService
import ru.socialeducationapps.worldmetrics.modules.democracy_index.service.impl.DemocracyIndexServiceImpl

@Module
@InstallIn(ViewModelComponent::class)
class DemocracyIndexModule {
    @Provides
    fun democracyIndexService(impl: DemocracyIndexServiceImpl): DemocracyIndexService = impl
        .also { it.filePath = FILE_PATH }

    companion object {
        private const val FILE_PATH = "indexes/DemocracyIndex.csv"
    }
}
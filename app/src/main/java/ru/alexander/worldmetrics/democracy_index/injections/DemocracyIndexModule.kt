package ru.alexander.worldmetrics.democracy_index.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexander.worldmetrics.democracy_index.service.api.DemocracyIndexService
import ru.alexander.worldmetrics.democracy_index.service.impl.DemocracyIndexServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class DemocracyIndexModule {
    @Provides
    fun democracyIndexService(impl: DemocracyIndexServiceImpl): DemocracyIndexService {
        impl.filePath = FILE_PATH
        return impl
    }

    companion object {
        private val FILE_PATH = "indexes/DemocracyIndex.csv"
    }
}
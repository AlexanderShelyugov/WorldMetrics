package ru.alexander.worldmetrics.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexander.worldmetrics.service.api.DemocracyIndexService
import ru.alexander.worldmetrics.service.impl.DemocracyIndexServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class DemocracyIndexModule {
    @Provides
    fun democracyIndexService(impl: DemocracyIndexServiceImpl): DemocracyIndexService {
        impl.fileName = FILE_PATH_DEMOCRACY_INDEX
        return impl
    }

    companion object {
        private val FILE_PATH_DEMOCRACY_INDEX = "indexes/DemocracyIndex.csv"
    }
}
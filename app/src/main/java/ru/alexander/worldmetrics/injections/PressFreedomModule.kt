package ru.alexander.worldmetrics.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexander.worldmetrics.service.api.PressFreedomService
import ru.alexander.worldmetrics.service.impl.PressFreedomServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class PressFreedomModule {
    @Provides
    fun pressFreedomService(impl: PressFreedomServiceImpl): PressFreedomService {
        impl.filePath = FILE_PATH
        return impl
    }

    companion object {
        private val FILE_PATH = "indexes/PressFreedomIndex.csv"
    }
}
package ru.alexander.worldmetrics.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexander.worldmetrics.service.api.CorruptionPerceptionsService
import ru.alexander.worldmetrics.service.impl.CorruptionPerceptionsServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class CorruptionPerceptionsModule {
    @Provides
    fun corruptionPercepsionsService(impl: CorruptionPerceptionsServiceImpl): CorruptionPerceptionsService {
        impl.filePath = FILE_PATH
        return impl
    }

    companion object {
        private val FILE_PATH = "indexes/CorruptionPerceptionsIndex.csv"
    }
}
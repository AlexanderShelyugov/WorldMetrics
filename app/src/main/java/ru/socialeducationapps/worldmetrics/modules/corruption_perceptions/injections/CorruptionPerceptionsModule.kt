package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.service.api.CorruptionPerceptionsService
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.service.impl.CorruptionPerceptionsServiceImpl

@Module
@InstallIn(ViewModelComponent::class)
class CorruptionPerceptionsModule {
    @Provides
    fun corruptionPerceptionsService(impl: CorruptionPerceptionsServiceImpl): CorruptionPerceptionsService =
        impl
            .also { it.filePath = FILE_PATH }

    companion object {
        private const val FILE_PATH = "indexes/CorruptionPerceptionsIndex.csv"
    }
}
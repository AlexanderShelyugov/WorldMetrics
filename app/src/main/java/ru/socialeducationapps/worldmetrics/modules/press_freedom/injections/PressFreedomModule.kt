package ru.socialeducationapps.worldmetrics.modules.press_freedom.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.impl.PressFreedomCsvService

@Module
@InstallIn(ViewModelComponent::class)
class PressFreedomModule {
    @Provides
    fun pressFreedomService(impl: PressFreedomCsvService): PressFreedomService = impl
        .also { it.filePath = FILE_PATH }

    companion object {
        private const val FILE_PATH = "indexes/PressFreedomIndex.csv"
    }
}
package ru.socialeducationapps.worldmetrics.modules.press_freedom.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.modules.press_freedom.service.impl.PressFreedomServiceImpl

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
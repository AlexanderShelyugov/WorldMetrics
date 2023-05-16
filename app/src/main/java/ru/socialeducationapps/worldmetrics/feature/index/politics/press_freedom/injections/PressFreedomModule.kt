package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.api.PressFreedomService
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.service.impl.PressFreedomRoomService

@Module
@InstallIn(ViewModelComponent::class)
class PressFreedomModule {
    @Provides
    fun pressFreedomService(impl: PressFreedomRoomService): PressFreedomService = impl
}
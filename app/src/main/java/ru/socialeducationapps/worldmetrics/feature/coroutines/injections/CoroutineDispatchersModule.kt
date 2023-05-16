package ru.socialeducationapps.worldmetrics.feature.coroutines.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.coroutines.impl.SimpleDispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatchersModule {
    @Singleton
    @Provides
    fun coroutineDispatcherProvider(): DispatcherProvider = SimpleDispatcherProvider()
}
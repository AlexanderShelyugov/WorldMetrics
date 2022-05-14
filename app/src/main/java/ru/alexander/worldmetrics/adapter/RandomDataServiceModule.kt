package ru.alexander.worldmetrics.adapter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RandomDataServiceModule {
    @Provides
    fun randomDataService(impl: RandomDataServiceImpl): RandomDataService {
        return impl
    }
}
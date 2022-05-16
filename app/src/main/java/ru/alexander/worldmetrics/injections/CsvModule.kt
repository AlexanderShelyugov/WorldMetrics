package ru.alexander.worldmetrics.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexander.worldmetrics.service.api.csv.CsvService
import ru.alexander.worldmetrics.service.impl.csv.CsvServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class CsvModule {
    @Provides
    fun csvService(impl: CsvServiceImpl): CsvService = impl
}
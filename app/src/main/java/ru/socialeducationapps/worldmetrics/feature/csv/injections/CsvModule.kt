package ru.socialeducationapps.worldmetrics.feature.csv.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.socialeducationapps.worldmetrics.feature.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.feature.csv.service.impl.CsvServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class CsvModule {
    @Provides
    fun csvService(impl: CsvServiceImpl): CsvService = impl
}
package ru.socialeducationapps.worldmetrics.modules.csv.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.socialeducationapps.worldmetrics.modules.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.modules.csv.service.impl.CsvServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class CsvModule {
    @Provides
    fun csvService(impl: CsvServiceImpl): CsvService = impl
}
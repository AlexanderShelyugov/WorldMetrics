package ru.socialeducationapps.worldmetrics.modules.gdp.injections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.socialeducationapps.worldmetrics.modules.csv.service.api.CsvService
import ru.socialeducationapps.worldmetrics.modules.gdp.service.api.GDPService
import ru.socialeducationapps.worldmetrics.modules.gdp.service.impl.GDPCSVService

@Module
@InstallIn(ViewModelComponent::class)
class GDPModule {
    @Provides
    fun gdpService(csvService: CsvService): GDPService =
        GDPCSVService(csvService)
            .apply { filePath = FILE_PATH }

    companion object {
        private const val FILE_PATH = "indexes/GDP.csv"
    }
}
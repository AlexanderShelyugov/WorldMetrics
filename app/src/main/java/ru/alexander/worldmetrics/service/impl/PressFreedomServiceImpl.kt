package ru.alexander.worldmetrics.service.impl

import ru.alexander.worldmetrics.service.api.PressFreedomService
import ru.alexander.worldmetrics.service.api.csv.CsvService
import javax.inject.Inject

class PressFreedomServiceImpl @Inject constructor(private val csvService: CsvService) :
    PressFreedomService {
    lateinit var filePath: String

    override fun getLastYearData(): Map<String, String> {
        TODO("Not yet implemented")
    }
}
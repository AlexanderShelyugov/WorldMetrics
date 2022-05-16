package ru.alexander.worldmetrics.service.impl

import ru.alexander.worldmetrics.service.api.PressFreedomService
import ru.alexander.worldmetrics.service.api.csv.CsvService
import javax.inject.Inject

class PressFreedomServiceImpl @Inject constructor(private val csvService: CsvService) :
    PressFreedomService {
    lateinit var filePath: String

    private companion object {
        const val MAX_YEAR = 2022
        const val COLUMN_COUNTRY_CODE = 0
        const val COLUMN_COUNTRY_NAME = 14
        const val COLUMN_INDEX_VALUE = 1
        const val COLUMN_YEAR = 19
    }

    override fun getLastYearData(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val processor: (Sequence<List<String>>) -> Unit = { rows ->
            rows
                .filter { MAX_YEAR == it[COLUMN_YEAR].toInt() }
                .map { it[COLUMN_COUNTRY_NAME] to it[COLUMN_INDEX_VALUE] }
                .associateTo(result) { it }
        }
        csvService.process(filePath, processor, ';')
        return result
    }
}
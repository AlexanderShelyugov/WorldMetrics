package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.room.entity.PressFreedomIndexValue

@Dao
interface PressFreedomDao {
    @Transaction
    @Query("SELECT iso3CountryCode, pressFreedom AS value FROM pressfreedomindexvalue WHERE year = $MAX_YEAR")
    fun getLastYearData(): List<SimpleCountryValue>

    @Transaction
    @Query("SELECT * FROM pressfreedomindexvalue WHERE iso3CountryCode = :country AND year = $MAX_YEAR")
    fun getLastYearData(country: String): PressFreedomIndexValue

    @Transaction
    @Query("SELECT * FROM pressfreedomindexvalue WHERE iso3CountryCode = :country")
    fun getAllData(country: String): List<PressFreedomIndexValue>

    private companion object {
        const val MAX_YEAR = 2022
    }
}
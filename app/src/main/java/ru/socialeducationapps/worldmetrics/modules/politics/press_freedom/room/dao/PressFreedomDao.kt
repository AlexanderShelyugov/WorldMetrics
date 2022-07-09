package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.room.dao

import androidx.room.Dao
import androidx.room.Query
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.room.entity.PressFreedomIndexValue

@Dao
interface PressFreedomDao {
    @Query("SELECT iso3CountryCode, pressFreedom AS value FROM pressfreedomindexvalue WHERE year = 2022")
    fun getLastYearData(): List<SimpleCountryValue>

    @Query("SELECT * FROM pressfreedomindexvalue WHERE iso3CountryCode = :country AND year = 2022")
    fun getLastYearData(country: String): PressFreedomIndexValue

    @Query("SELECT * FROM pressfreedomindexvalue WHERE iso3CountryCode = :country")
    fun getAllData(country: String): List<PressFreedomIndexValue>
}
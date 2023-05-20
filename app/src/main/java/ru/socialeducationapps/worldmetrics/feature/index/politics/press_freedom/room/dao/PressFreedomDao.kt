package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.room.entity.PressFreedomIndexValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.CountryFeatureValue

@Dao
interface PressFreedomDao {
    @Transaction
    @Query("SELECT iso3CountryCode, pressFreedom AS value FROM pressfreedomindexvalue WHERE year = $MAX_YEAR")
    fun getLastYearData(): List<CountryFeatureValue>

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
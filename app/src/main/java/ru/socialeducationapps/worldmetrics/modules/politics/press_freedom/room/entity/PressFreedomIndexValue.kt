package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.room.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["iso3CountryCode", "year"],
    indices = [Index(value = ["iso3CountryCode", "year"])]
)
data class PressFreedomIndexValue(
    val iso3CountryCode: String,
    val year: Int,
    val pressFreedom: Float,
    val politicalContext: Float,
    val economicContext: Float,
    val legalContext: Float,
    val socialContext: Float,
    val safety: Float,
)

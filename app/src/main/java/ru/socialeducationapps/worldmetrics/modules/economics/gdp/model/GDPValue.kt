package ru.socialeducationapps.worldmetrics.modules.economics.gdp.model

data class GDPValue(
    val iso3Code: String,
    val year: Int,
    val valueMlnUsd: Float,
    val valueUsdCap: Float,
)
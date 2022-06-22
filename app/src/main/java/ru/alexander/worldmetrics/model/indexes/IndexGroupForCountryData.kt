package ru.alexander.worldmetrics.model.indexes

data class IndexGroupForCountryData(
    val name: Int,
    val color: Int,
    val itemColor: Int,
    val indexes: List<IndexForCountryData>,
)
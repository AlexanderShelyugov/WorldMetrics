package ru.socialeducationapps.worldmetrics.feature.indexes.all.model

data class DataBindingForIndexGroup(
    val name: Int,
    val color: Int,
    val itemColor: Int,
    val indexes: List<DataBindingForIndex>,
)
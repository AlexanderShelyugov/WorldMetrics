package ru.socialeducationapps.worldmetrics.deprecated.search

interface SearchCriteria<T> {
    fun search(
        allItems: List<T>,
        searchKeyExtractor: (T) -> String,
        searchQuery: String
    ): List<T>
}
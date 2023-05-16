package ru.socialeducationapps.worldmetrics.deprecated.search

class TextSearchCriteria<T> : SearchCriteria<T> {
    override fun search(
        allItems: List<T>,
        searchKeyExtractor: (T) -> String,
        searchQuery: String
    ): List<T> {
        if (searchQuery.isEmpty()) {
            return allItems
        }
        val result = allItems.asSequence()
            .map {
                val key = searchKeyExtractor.invoke(it)
                key.indexOf(searchQuery, ignoreCase = true) to it
            }
            .filter { 0 <= it.first }
            .sortedBy { it.first }
            .map { it.second }
            .toList()
        return result
    }
}
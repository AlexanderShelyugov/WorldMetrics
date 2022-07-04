package ru.socialeducationapps.worldmetrics.model.indexes

import ru.socialeducationapps.worldmetrics.R

class AllIndexes private constructor() {
    companion object {
        // Demographic indexes
        val DEMOGRAPHIC_INDEX_GROUP = IndexGroupForCountryData(
            name = R.string.index_group_name_demographic,
            color = R.color.index_group_color_demographic,
            itemColor = R.color.index_group_color_item_demographic,
            indexes = listOf(
                IndexForCountryData(R.string.index_name_population)
            )
        )

        // Political indexes
        val POLITICAL_INDEX_GROUP = IndexGroupForCountryData(
            name = R.string.index_group_name_political,
            color = R.color.index_group_color_political,
            itemColor = R.color.index_group_color_item_political,
            indexes = listOf(
                IndexForCountryData(R.string.index_name_corruption_perceptions),
                IndexForCountryData(R.string.index_name_democracy),
                IndexForCountryData(R.string.index_name_press_freedom)
            )
        )

        // Economical indexes
        val ECONOMICAL_INDEX_GROUP = IndexGroupForCountryData(
            name = R.string.index_group_name_economic,
            color = R.color.index_group_color_economic,
            itemColor = R.color.index_group_color_item_economic,
            indexes = listOf(
                IndexForCountryData(R.string.index_name_gdp),
            )
        )

        val ALL_INDEXES = listOf(
            DEMOGRAPHIC_INDEX_GROUP,
            POLITICAL_INDEX_GROUP,
            ECONOMICAL_INDEX_GROUP
        )
    }
}
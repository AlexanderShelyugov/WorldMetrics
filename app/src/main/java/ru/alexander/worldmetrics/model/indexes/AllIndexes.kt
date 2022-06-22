package ru.alexander.worldmetrics.model.indexes

import ru.alexander.worldmetrics.R

class AllIndexes private constructor() {
    companion object {
        val ALL_INDEXES: List<IndexGroupForCountryData>

        init {
            val indexes = mutableListOf<IndexGroupForCountryData>()

            // Demographic indexes
            indexes.add(
                IndexGroupForCountryData(
                    name = R.string.index_group_name_demographic,
                    color = R.color.index_group_color_demographic,
                    itemColor = R.color.index_group_color_item_demographic,
                    indexes = listOf(
                        IndexForCountryData(R.string.index_name_demographics)
                    )
                )
            )
            // Political indexes
            indexes.add(
                IndexGroupForCountryData(
                    name = R.string.index_group_name_political,
                    color = R.color.index_group_color_political,
                    itemColor = R.color.index_group_color_item_political,
                    indexes = listOf(
                        IndexForCountryData(R.string.index_name_corruption_perceptions),
                        IndexForCountryData(R.string.index_name_democracy),
                        IndexForCountryData(R.string.index_name_press_freedom)
                    )
                )
            )

            // Economical indexes
            indexes.add(
                IndexGroupForCountryData(
                    name = R.string.index_group_name_economic,
                    color = R.color.index_group_color_economic,
                    itemColor = R.color.index_group_color_item_economic,
                    indexes = listOf(
                        IndexForCountryData(R.string.index_name_gdp),
                        IndexForCountryData(R.string.index_name_some_business),
                    )
                )
            )

            ALL_INDEXES = indexes.toList()
        }
    }
}
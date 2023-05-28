package ru.socialeducationapps.worldmetrics.feature.indexes.common.model

data class IndexFeaturesLayout<IndexValueType>(
    val yearFunction: FeatureExtractor<IndexValueType>,
    /**
     * Ordered mapping of index feature's id to a function that extracts it's value
     */
    val features: List<Pair<Int, FeatureExtractor<IndexValueType>>>
)
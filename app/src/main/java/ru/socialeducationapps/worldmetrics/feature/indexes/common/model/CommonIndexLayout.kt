package ru.socialeducationapps.worldmetrics.feature.indexes.common.model

data class CommonIndexLayout<IndexValueType>(
    val yearFunction: FeatureExtractor<IndexValueType>,
    val features: List<Pair<Int, FeatureExtractor<IndexValueType>>>
) {
    val featuresNumber
        get() = features.size

    fun featureName(position: Int) = features[position].first
}
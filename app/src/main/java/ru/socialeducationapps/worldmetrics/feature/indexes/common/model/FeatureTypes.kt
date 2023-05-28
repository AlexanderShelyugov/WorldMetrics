package ru.socialeducationapps.worldmetrics.feature.indexes.common.model

typealias FeatureValue = Float

typealias FeatureRange = Pair<FeatureValue, FeatureValue>

typealias FeatureMedianRange = Triple<FeatureValue, FeatureValue, FeatureValue>

typealias FeatureExtractor<T> = (T) -> FeatureValue

fun FeatureRange.toFeatureMedianRange() =
    Triple(this.first, (this.first + this.second) / 2, this.second)
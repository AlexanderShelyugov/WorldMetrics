package ru.socialeducationapps.worldmetrics.feature.indexes.common.model

typealias FeatureValue = Float

typealias FeatureRange = Pair<FeatureValue, FeatureValue>

typealias FeatureExtractor<T> = (T) -> FeatureValue
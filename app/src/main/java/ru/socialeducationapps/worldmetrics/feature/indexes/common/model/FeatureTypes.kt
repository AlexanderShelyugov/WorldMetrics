package ru.socialeducationapps.worldmetrics.feature.indexes.common.model

typealias FeatureRange = Pair<Float, Float>

typealias FeatureExtractor<T> = (T) -> Float
package ru.socialeducationapps.worldmetrics.modules.indexes.model

typealias FeatureRange = Pair<Float, Float>

typealias FeatureExtractor<T> = (T) -> Float
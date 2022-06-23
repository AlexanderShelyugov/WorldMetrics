package ru.alexander.worldmetrics.model.indexes

typealias FeatureRange = Pair<Float, Float>

typealias FeatureExtractor<T> = (T) -> Float
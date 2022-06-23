package ru.alexander.worldmetrics.model.indexes

class CommonIndexRoutines private constructor() {
    companion object {
        fun <T> prepareExtractorsMap(
            ranges: List<FeatureRange>,
            featuresLayout: List<Pair<Int, FeatureExtractor<T>>>
        ): Map<FeatureRange, FeatureExtractor<T>> {
            var i = 0
            return ranges.asSequence()
                .map { it to featuresLayout[i++].second }
                .toMap()
        }
    }
}
package ru.alexander.worldmetrics.model

class ExtractorsFactory {
    companion object {
        fun getDemocracyIndexExtractor(): DemocracyIndexExtractor {
            return DemocracyIndexExtractorImpl("FILE_PATH_DEMOCRACY_INDEX")
        }
    }
}
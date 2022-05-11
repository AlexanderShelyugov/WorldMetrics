package ru.alexander.worldmetrics.model

class ExtractorsFactory {
    companion object {
        private val FILE_PATH_DEMOCRACY_INDEX = "indexes/DemocracyIndex.csv"

        fun getDemocracyIndexExtractor(): DemocracyIndexExtractor {
            return DemocracyIndexExtractorImpl(FILE_PATH_DEMOCRACY_INDEX)
        }
    }
}
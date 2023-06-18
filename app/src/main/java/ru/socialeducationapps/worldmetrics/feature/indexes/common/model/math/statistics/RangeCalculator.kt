package ru.socialeducationapps.worldmetrics.feature.indexes.common.model.math.statistics

class RangeCalculator {
    companion object {
        fun calculateMinMedianMax(number: List<Float>): Triple<Float, Float, Float> {
            val finiteNumbers = number.filter { it.isFinite() }.takeIf { it.isNotEmpty() }
                ?: emptyList()
            return Triple(
                finiteNumbers.minOrNull() ?: 0f,
                calculateMedian(finiteNumbers),
                finiteNumbers.maxOrNull() ?: 0f
            )
        }

        private fun calculateMedian(numbers: List<Float>) = numbers
            .filter { it.isFinite() }
            .sorted()
            .takeIf { it.isNotEmpty() }
            ?.let {
                if (it.size % 2 == 0)
                    (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
                else
                    it[it.size / 2]
            } ?: 0f
    }
}
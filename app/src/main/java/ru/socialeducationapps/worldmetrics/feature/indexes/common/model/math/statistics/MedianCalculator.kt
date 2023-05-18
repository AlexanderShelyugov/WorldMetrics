package ru.socialeducationapps.worldmetrics.feature.indexes.common.model.math.statistics

class MedianCalculator {
    companion object {
        fun calculate(numbers: List<Float>) = numbers.sorted().let {
            if (it.size % 2 == 0)
                (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
            else
                it[it.size / 2]
        }
    }
}
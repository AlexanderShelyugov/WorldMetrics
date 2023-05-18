package ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color

private typealias ColorRange = Pair<ColorType, ColorType>
internal typealias ColorType = Int
internal typealias ColorComponentType = Int

class ColorOfDataCalculator(
    colorRange: Pair<ColorType, ColorType>,
    private val minColor: ColorType = colorRange.first,
    private val maxColor: ColorType = colorRange.second,
    private val minValue: Float,
    private val medianValue: Float,
    private val maxValue: Float
) {
    fun getColorFor(value: Float): ColorType = TODO()
}
package ru.socialeducationapps.worldmetrics.feature.helper.view

import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineDataSet.Mode.HORIZONTAL_BEZIER
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureExtractor
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorInRangeCalculator
import java.lang.Float.NaN

class LabelValueChartView<T>(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private val _label: TextView
    val label: TextView
        get() = _label
    private val value: TextView
    private val chart: LineChart

    private var allData: List<T> = emptyList()
    private var keyExtractor: FeatureExtractor<T> = { NaN }
    private var valueExtractor: FeatureExtractor<T> = { NaN }
    private var calculator: ColorInRangeCalculator? = null
    private lateinit var range: FeatureRange

    init {
        inflate(getContext(), R.layout.label_value_chart_view_content, this)
        _label = findViewById(R.id.tv_label)
        value = findViewById(R.id.tv_value)
        chart = findViewById(R.id.lc_chart)
        chart.run {
            legend.isEnabled = false
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            description.isEnabled = false
            setDrawGridBackground(false)
            setDrawBorders(false)
            setBackgroundColor(TRANSPARENT)
            setGridBackgroundColor(TRANSPARENT)
            setTouchEnabled(false)
            isDragEnabled = false
            setScaleEnabled(false)
            setPinchZoom(false)
        }
    }

    fun setLabelText(strId: Int) {
        _label.text = context.getString(strId)
    }

    fun setExtractors(k: FeatureExtractor<T>, v: FeatureExtractor<T>) {
        keyExtractor = k
        valueExtractor = v
    }

    fun setData(items: List<T>) {
        allData = items
    }

    fun setRangeColors(calculator: ColorInRangeCalculator, range: FeatureRange) {
        this.calculator = calculator
        this.range = range
    }

    fun refresh() {
        val feature = allData
            .takeIf { it.isNotEmpty() }
            ?.let { data -> data.sortedBy(keyExtractor).reversed()[0] }
            ?.run { valueExtractor(this) }
            ?.takeIf { featureValue -> featureValue.isFinite() }
        val valueText: String
        val valueColor: Int

        if (feature == null) {
            valueText = context.getString(R.string.no_data)
            valueColor = context.getColor(R.color.colorOnPrimary)
        } else {
            valueText = feature.toBigDecimal().toPlainString()
            valueColor =
                calculator?.evalColor(range, feature) ?: context.getColor(R.color.colorOnPrimary)
        }
        value.text = valueText
        value.setTextColor(valueColor)

        val entries = allData.asSequence()
            .map {
                val x = keyExtractor(it)
                val y = valueExtractor(it)
                Entry(x, y)
            }
            .toList()
        chart.apply {
            data = LineData(LineDataSet(entries, "").also {
                it.setDrawCircles(false)
                it.setDrawValues(false)
                it.color = context.getColor(R.color.holo_blue_bright)
                it.lineWidth = 2f
                it.mode = HORIZONTAL_BEZIER
            })
            isVisible = entries.isNotEmpty()
        }
    }
}
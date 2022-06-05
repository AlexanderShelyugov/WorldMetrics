package ru.alexander.worldmetrics.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import ru.alexander.worldmetrics.R
import java.lang.Float.NaN

typealias FeatureExtractor<T> = (T) -> Float

class LabelValueChartView<T>(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {

    private val label: TextView
    private val value: TextView
    private val chart: LineChart

    private var allData: List<T> = emptyList()

    private var labelText: String = ""
    private var chartLabel: String = ""
    private var keyExtractor: FeatureExtractor<T> = { NaN }
    private var valueExtractor: FeatureExtractor<T> = { NaN }

    init {
        inflate(getContext(), R.layout.label_value_chart_view_content, this)
        label = findViewById(R.id.tv_label)
        value = findViewById(R.id.tv_value)
        chart = findViewById(R.id.lc_chart)

        chart.setDrawGridBackground(false)
        chart.setDrawBorders(false)
        chart.setBackgroundColor(Color.TRANSPARENT)
        chart.setGridBackgroundColor(Color.TRANSPARENT)
        chart.setTouchEnabled(false)
        chart.isDragEnabled = false
        chart.setScaleEnabled(false)
        chart.setPinchZoom(false)
    }

    fun setLabelText(strId: Int) {
        labelText = context.getString(strId)
    }

    fun setExtractors(k: FeatureExtractor<T>, v: FeatureExtractor<T>) {
        keyExtractor = k
        valueExtractor = v
    }

    fun setData(items: List<T>) {
        allData = items
        label.text = labelText


        val topDataItem = if (items.isEmpty()) null else items.sortedBy(keyExtractor)[0]
        topDataItem?.let {
            value.text = valueExtractor(it).toString()
        } ?: run {
            value.text = ""
        }

        val entries = items.asSequence()
            .map {
                val x = keyExtractor(it)
                val y = valueExtractor(it)
                Entry(x, y)
            }
            .toList()
        chart.data = LineData(LineDataSet(entries, chartLabel))
    }
}
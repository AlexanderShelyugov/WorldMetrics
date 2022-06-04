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
import ru.alexander.worldmetrics.model.DemocracyIndexValue
import ru.alexander.worldmetrics.model.DemocracyIndexValue.Companion.emptyValue
import java.lang.Float.NaN

typealias Item = DemocracyIndexValue

class LabelValueChartView(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {

    private val label: TextView
    private val value: TextView
    private val chart: LineChart

    private var allData: List<Item> = emptyList()
    private var topDataItem: Item = emptyValue()

    private var labelText: String = ""
    private var keyExtractor: (Item) -> Float = { NaN }
    private var valueExtractor: (Item) -> Float = { NaN }
    private var chartLabel: String = ""

    init {
        inflate(getContext(), R.layout.label_value_chart_view, this)
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

    fun setExtractors(f: (Item) -> Float) {
        keyExtractor = f
    }

    fun setValueExtractor(f: (Item) -> Float) {
        valueExtractor = f
    }

    fun setData(items: List<Item>) {
        allData = items
        topDataItem = if (items.isEmpty()) emptyValue() else items.sortedBy(keyExtractor)[0]

        topDataItem.run {
            label.text = labelText
            value.text = valueExtractor(this).toString()
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
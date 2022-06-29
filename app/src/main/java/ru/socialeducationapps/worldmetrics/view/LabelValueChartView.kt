package ru.socialeducationapps.worldmetrics.view

import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineDataSet.Mode.HORIZONTAL_BEZIER
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureExtractor
import java.lang.Float.NaN

class LabelValueChartView<T>(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private val label: TextView
    private val value: TextView
    private val chart: LineChart

    private var allData: List<T> = emptyList()
    private var keyExtractor: FeatureExtractor<T> = { NaN }
    private var valueExtractor: FeatureExtractor<T> = { NaN }

    init {
        inflate(getContext(), R.layout.label_value_chart_view_content, this)
        label = findViewById(R.id.tv_label)
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
        label.text = context.getString(strId)
    }

    fun setExtractors(k: FeatureExtractor<T>, v: FeatureExtractor<T>) {
        keyExtractor = k
        valueExtractor = v
    }

    fun setData(items: List<T>) {
        allData = items
    }

    fun setValueColor(color: Int) {
        value.setTextColor(color)
    }

    fun refresh() {
        val topDataItem =
            if (allData.isEmpty()) null else allData.sortedBy(keyExtractor).reversed()[0]
        topDataItem.let {
            value.text = if (it != null) valueExtractor(it).toString() else ""
        }

        val entries = allData.asSequence()
            .map {
                val x = keyExtractor(it)
                val y = valueExtractor(it)
                Entry(x, y)
            }
            .toList()
        chart.data = LineData(LineDataSet(entries, "").also {
            it.setDrawCircles(false)
            it.setDrawValues(false)
            it.lineWidth = 2f
            it.mode = HORIZONTAL_BEZIER
        })
    }
}
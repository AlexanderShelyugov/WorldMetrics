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
import ru.socialeducationapps.worldmetrics.feature.indexes.common.view.color.ColorOfDataCalculator

class LabelValueChartView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private val label: TextView
    private val value: TextView
    private val chart: LineChart

    private var state: LVCVState? = null
    private var calculator: ColorOfDataCalculator? = null

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

    fun setState(state: LVCVState) {
        this.state = state
    }

    fun setColorCalculator(calculator: ColorOfDataCalculator?) {
        this.calculator = calculator
    }

    fun refresh() {
        label.text = state?.labelText ?: ""
        val valueToShow = state?.data
            ?.takeIf { it.isNotEmpty() }
            ?.let { it[it.lastIndex].second }
            ?.takeIf { lastValue -> lastValue.isFinite() }
        val valueText: String
        val valueColor: Int

        if (valueToShow == null) {
            valueText = context.getString(R.string.no_data)
            valueColor = context.getColor(R.color.colorOnPrimary)
        } else {
            valueText = valueToShow.toBigDecimal().toPlainString()
            valueColor =
                calculator?.getColorFor(valueToShow) ?: context.getColor(R.color.holo_blue_bright)
        }
        value.text = valueText
        value.setTextColor(valueColor)

        val entries = state?.data!!.asSequence()
            .filter { it.first.isFinite() }
            .filter { it.second.isFinite() }
            .sortedBy { it.first }
            .map { (x, y) -> Entry(x, y) }
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

    companion object {
        /**
         * State of the LabelValueChartView
         */
        data class LVCVState(
            /**
             * Text to show in label
             */
            val labelText: String,
            /**
             * Ordered sequence of X,Y values to show
             */
            val data: List<Pair<Float, Float>>
        )
    }
}
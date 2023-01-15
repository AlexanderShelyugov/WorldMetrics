package ru.socialeducationapps.worldmetrics.fragment.home_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.model.indexes.AllIndexes.Companion.ALL_INDEXES
import ru.socialeducationapps.worldmetrics.viewmodel.CountryScoreViewModel

@AndroidEntryPoint
class CountryRadarFragment : InjectableFragment(R.layout.country_radar_layout) {
    private val model by viewModels<CountryScoreViewModel>()
    private lateinit var chart: RadarChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRadar()
        setRadarData()
    }

    private fun configureRadar() {
        chart = requireView().findViewById(R.id.rc_content)
        chart.run {
            isRotationEnabled = false
            legend.isEnabled = false
            description.isEnabled = false

            xAxis.also {
                it.setDrawGridLines(false)
                it.setDrawAxisLine(false)
                it.setDrawGridLinesBehindData(false)
                it.setDrawLimitLinesBehindData(false)
                it.textColor = requireContext().getColor(R.color.colorOnPrimary)
                it.textSize = 10f
            }
            yAxis.also {
                it.axisMinimum = 0f
                it.axisMaximum = 10f
                it.isEnabled = false
                it.setDrawGridLines(false)
                it.setDrawZeroLine(false)
                it.setDrawAxisLine(false)
            }
            setDrawMarkers(false)
            extraTopOffset = 10f
            extraBottomOffset = 0f

            invalidate()
        }
    }

    private fun setRadarData() {
        val labels = mutableListOf<String>()
        val items = mutableListOf<RadarEntry>()
        ALL_INDEXES
            .stream()
            .forEach {
                labels += getString(it.name).substring(0, 1)
                items += RadarEntry((2..10).random().toFloat())
            }
        val dataSet = RadarDataSet(items, "").run {
            setDrawFilled(true)
            setDrawValues(false)
            isHighlightEnabled = false
            this
        }

        val data = RadarData(listOf(dataSet)).also {
            it.setDrawValues(false)
        }

        chart.data = data
        val formatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return labels[value.toInt() % labels.size]
            }
        }
        chart.xAxis.valueFormatter = formatter
        chart.invalidate()
    }
}
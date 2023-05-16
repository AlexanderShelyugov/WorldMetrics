package ru.socialeducationapps.worldmetrics.feature.country.score.fragment

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
import ru.socialeducationapps.worldmetrics.feature.country.current.viewmodel.CurrentCountryViewModel
import ru.socialeducationapps.worldmetrics.feature.country.score.viewmodel.CountryScoreViewModel
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment

@AndroidEntryPoint
class CountryRadarFragment : InjectableFragment(R.layout.country_radar_layout) {
    private val model by viewModels<CountryScoreViewModel>()
    private val countryModel by viewModels<CurrentCountryViewModel>()
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

    private fun hideRadar() {
        chart.visibility = View.GONE
        chart.invalidate()
    }

    private fun setRadarData() {
        countryModel.currentCountryCode.observe(viewLifecycleOwner) { country ->
            if (country.isBlank()) {
                hideRadar()
                return@observe
            }
            val labels = mutableListOf<String>()
            val items = mutableListOf<RadarEntry>()
            model.getCountryScores(country).forEach { (name, score) ->
                labels += requireContext().getString(name).first().toString()
                items += RadarEntry(score.toFloat())
            }
            chart.visibility = View.VISIBLE

            val dataSet = RadarDataSet(items, "").run {
                setDrawFilled(true)
                setDrawValues(false)
                isHighlightEnabled = false
                this
            }

            chart.data = RadarData(listOf(dataSet)).also {
                it.setDrawValues(false)
            }
            val formatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return labels[value.toInt() % labels.size]
                }
            }
            chart.xAxis.valueFormatter = formatter
            chart.invalidate()
        }
    }
}
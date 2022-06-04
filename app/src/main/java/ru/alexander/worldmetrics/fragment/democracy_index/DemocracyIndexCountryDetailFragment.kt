package ru.alexander.worldmetrics.fragment.democracy_index

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.InjectableFragment
import ru.alexander.worldmetrics.view.LabelValueChartView
import ru.alexander.worldmetrics.viewmodel.democracy_index.DemocracyIndexCountryDetailViewModel

class DemocracyIndexCountryDetailFragment :
    InjectableFragment(R.layout.democracy_index_country_detail) {

    private lateinit var democracyIndexView: LabelValueChartView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCountryName = view.findViewById<TextView>(R.id.tv_di_cn)

        democracyIndexView = view.findViewById(R.id.lvcv_di)
        democracyIndexView.setExtractors { it.year.toFloat() }
        democracyIndexView.setLabelText(getString(R.string.democracy_index_name))
        democracyIndexView.setValueExtractor { it.democracyIndex }

        val tvElectoralProcessAndPluralism =
            view.findViewById<TextView>(R.id.tv_di_epap)
        val tvFunctioningOfGovernment =
            view.findViewById<TextView>(R.id.tv_di_fog)
        val tvPoliticalParticipation =
            view.findViewById<TextView>(R.id.tv_di_pp)
        val tvPoliticalCulture =
            view.findViewById<TextView>(R.id.tv_di_pc)
        val tvCivilLiberties =
            view.findViewById<TextView>(R.id.tv_di_cl)

        val model: DemocracyIndexCountryDetailViewModel by activityViewModels()
        model.lastYearData.observe(viewLifecycleOwner) {
            tvCountryName.text = it.country
            tvElectoralProcessAndPluralism.text =
                it.electoralProcessAndPluralism.toString()
            tvFunctioningOfGovernment.text = it.functioningOfGovernment.toString()
            tvPoliticalParticipation.text = it.politicalParticipation.toString()
            tvPoliticalCulture.text = it.politicalCulture.toString()
            tvCivilLiberties.text = it.civilLiberties.toString()
        }

        model.allData.observe(viewLifecycleOwner) { indexPerYear ->
            democracyIndexView.setData(indexPerYear)
        }

        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        val country = args.countryName
        model.setCountry(country)
    }

    /*
    private fun initCharts(view: View, entries: List<Entry>) {
        val chart = view.findViewById<LineChart>(R.id.lc_di_chart)
        val democracyIndexData = LineDataSet(entries, "Democracy Index")
        chart.data = LineData(democracyIndexData)
        chart.isAutoScaleMinMaxEnabled = true
        chart.setDrawGridBackground(false)
        chart.setDrawBorders(false)
        chart.setBackgroundColor(Color.TRANSPARENT)
        chart.setGridBackgroundColor(Color.TRANSPARENT)
        chart.setTouchEnabled(false)
        chart.isDragEnabled = false
        chart.setScaleEnabled(false)
        chart.setPinchZoom(false)
    }
    */
}
package ru.alexander.worldmetrics.fragment.democracy_index

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.InjectableFragment
import ru.alexander.worldmetrics.model.DemocracyIndexValue
import ru.alexander.worldmetrics.view.LabelValueChartView
import ru.alexander.worldmetrics.viewmodel.democracy_index.DemocracyIndexCountryDetailViewModel

class DemocracyIndexCountryDetailFragment :
    InjectableFragment(R.layout.democracy_index_country_detail) {

    private lateinit var democracyIndexView: LabelValueChartView
    private lateinit var electoralProcessAndPluralismView: LabelValueChartView
    private lateinit var functioningOfGovernment: LabelValueChartView
    private lateinit var politicalParticipation: LabelValueChartView
    private lateinit var politicalCulture: LabelValueChartView
    private lateinit var civilLiberties: LabelValueChartView

    private lateinit var allIndexes: Collection<LabelValueChartView>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCountryName = view.findViewById<TextView>(R.id.tv_country_name)

        val indexes = mutableListOf<LabelValueChartView>()
        democracyIndexView = view.findViewById<LabelValueChartView>(R.id.lvcv_di).run {
            setExtractors(YEAR_FUNCTION)
            setLabelText(R.string.democracy_index_name)
            setValueExtractor { it.democracyIndex }
            indexes.add(this)
            this
        }
        electoralProcessAndPluralismView =
            view.findViewById<LabelValueChartView>(R.id.lvcv_epap).run {
                setExtractors(YEAR_FUNCTION)
                setLabelText(R.string.electoral_process_and_pluralism)
                setValueExtractor { it.electoralProcessAndPluralism }
                indexes.add(this)
                this
            }
        functioningOfGovernment = view.findViewById<LabelValueChartView>(R.id.lvcv_fog).run {
            setExtractors(YEAR_FUNCTION)
            setLabelText(R.string.functioning_of_government)
            setValueExtractor { it.functioningOfGovernment }
            indexes.add(this)
            this
        }
        politicalParticipation = view.findViewById<LabelValueChartView>(R.id.lvcv_pp).run {
            setExtractors(YEAR_FUNCTION)
            setLabelText(R.string.functioning_of_government)
            setValueExtractor { it.functioningOfGovernment }
            indexes.add(this)
            this
        }
        politicalCulture = view.findViewById<LabelValueChartView>(R.id.lvcv_pc).run {
            setExtractors(YEAR_FUNCTION)
            setLabelText(R.string.political_culture)
            setValueExtractor { it.politicalCulture }
            indexes.add(this)
            this
        }
        civilLiberties = view.findViewById<LabelValueChartView>(R.id.lvcv_cl).run {
            setExtractors(YEAR_FUNCTION)
            setLabelText(R.string.civil_liberties)
            setValueExtractor { it.civilLiberties }
            indexes.add(this)
            this
        }
        allIndexes = indexes.toList()


        val model: DemocracyIndexCountryDetailViewModel by activityViewModels()
        model.allData.observe(viewLifecycleOwner) { indexPerYear ->
            allIndexes.forEach {
                it.setData(indexPerYear)
            }
        }
        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        val country = args.countryName
        tvCountryName.text = country
        model.setCountry(country)
    }

    private companion object {
        val YEAR_FUNCTION: (DemocracyIndexValue) -> Float = { it.year.toFloat() }
    }
}
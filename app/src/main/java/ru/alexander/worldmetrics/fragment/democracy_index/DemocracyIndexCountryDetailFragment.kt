package ru.alexander.worldmetrics.fragment.democracy_index

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.InjectableFragment
import ru.alexander.worldmetrics.model.CountriesData.Companion.getNameByCode
import ru.alexander.worldmetrics.model.DemocracyIndexValue
import ru.alexander.worldmetrics.view.LabelValueChartView
import ru.alexander.worldmetrics.viewmodel.democracy_index.DemocracyIndexCountryDetailViewModel

private typealias Index = DemocracyIndexValue
private typealias ValueFunction = (Index) -> Float

class DemocracyIndexCountryDetailFragment :
    Fragment(R.layout.country_detail_indexes) {

    private var allIndexes: Collection<LabelValueChartView<Index>> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        val countryCode = args.countryCode

        view.findViewById<TextView>(R.id.tv_country_name).run { text = getNameByCode(countryCode) }

        val indexesContainer = view.findViewById<ViewGroup>(R.id.ll_indexes)
        allIndexes = INDEXES_TO_SHOW.asSequence()
            .map { createIndexView(it.first, it.second) }
            .onEach(indexesContainer::addView)
            .toList()

        val model: DemocracyIndexCountryDetailViewModel by activityViewModels()
        model.allData.observe(viewLifecycleOwner) { indexPerYear ->
            allIndexes.forEach {
                it.setData(indexPerYear)
            }
        }
        model.setCountry(countryCode)
    }

    private fun createIndexView(
        labelText: Int,
        valueExtractor: ValueFunction
    ): LabelValueChartView<Index> {
        val view =
            layoutInflater.inflate(
                R.layout.label_value_chart_view,
                null
            ) as LabelValueChartView<Index>
        view.setLabelText(labelText)
        view.setExtractors(YEAR_FUNCTION, valueExtractor)
        return view
    }

    private companion object {
        val YEAR_FUNCTION: ValueFunction = { it.year.toFloat() }

        val INDEXES_TO_SHOW: List<Pair<Int, ValueFunction>> = listOf(
            R.string.democracy_index_name to { it.democracyIndex },
            R.string.electoral_process_and_pluralism to { it.electoralProcessAndPluralism },
            R.string.functioning_of_government to { it.functioningOfGovernment },
            R.string.political_participation to { it.politicalParticipation },
            R.string.political_culture to { it.politicalCulture },
            R.string.civil_liberties to { it.civilLiberties },
        )
    }
}
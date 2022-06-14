package ru.alexander.worldmetrics.fragment.press_freedom

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.CountriesData.Companion.getNameByCode
import ru.alexander.worldmetrics.model.press_freedom.PressFreedomData.Companion.INDEXES_TO_SHOW
import ru.alexander.worldmetrics.model.press_freedom.PressFreedomData.Companion.YEAR_FUNCTION
import ru.alexander.worldmetrics.model.press_freedom.PressFreedomValue
import ru.alexander.worldmetrics.view.LabelValueChartView
import ru.alexander.worldmetrics.viewmodel.press_freedom.PressFreedomCountryDetailViewModel

private typealias Index = PressFreedomValue
private typealias ValueFunction = (Index) -> Float

class PressFreedomCountryDetailFragment :
    Fragment(R.layout.country_detail_indexes) {
    private var allIndexes: Collection<LabelValueChartView<Index>> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: PressFreedomCountryDetailFragmentArgs by navArgs()
        val countryCode = args.countryCode

        view.findViewById<TextView>(R.id.tv_country_name).run { text = getNameByCode(countryCode) }

        val indexesContainer = view.findViewById<ViewGroup>(R.id.ll_indexes)
        allIndexes = INDEXES_TO_SHOW.asSequence()
            .map { createIndexView(it.first, it.second, indexesContainer) }
            .onEach(indexesContainer::addView)
            .toList()

        val model: PressFreedomCountryDetailViewModel by activityViewModels()
        model.allData.observe(viewLifecycleOwner) { indexPerYear ->
            allIndexes.forEach {
                it.setData(indexPerYear)
            }
        }
        model.setCountry(countryCode)
    }

    private fun createIndexView(
        labelText: Int,
        valueExtractor: ValueFunction,
        container: ViewGroup
    ): LabelValueChartView<Index> {
        val view =
            layoutInflater.inflate(
                R.layout.label_value_chart_view,
                container,
                false
            ) as LabelValueChartView<Index>
        view.setLabelText(labelText)
        view.setExtractors(YEAR_FUNCTION, valueExtractor)
        return view
    }
}
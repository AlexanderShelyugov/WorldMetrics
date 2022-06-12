package ru.alexander.worldmetrics.fragment.democracy_index

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.fragment.democracy_index.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewFragmentToDemocracyIndexCountryDetailFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.alexander.worldmetrics.viewmodel.democracy_index.DemocracyIndexOverviewViewModel

class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model: DemocracyIndexOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<Map<String, String>> = model.lastYearData

    override fun getValueRange(): Pair<Float, Float> = model.getValueRange()

    override fun onCountryClick(country: String) {
        navigateTo(
            findNavController(),
            actionDemocracyIndexOverviewFragmentToDemocracyIndexCountryDetailFragment(country)
        )
    }
}
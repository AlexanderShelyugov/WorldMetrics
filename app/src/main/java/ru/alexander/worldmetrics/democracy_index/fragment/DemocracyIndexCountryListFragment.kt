package ru.alexander.worldmetrics.democracy_index.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.democracy_index.fragment.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewToDemocracyIndexCountryDetail
import ru.alexander.worldmetrics.democracy_index.viewmodel.DemocracyIndexOverviewViewModel
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo

class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model: DemocracyIndexOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<Map<String, String>> = model.lastYearData

    override fun getValueRange(): Pair<Float, Float> = model.getValueRange()

    override fun onCountryClick(country: String) {
        navigateTo(
            findNavController(),
            actionDemocracyIndexOverviewToDemocracyIndexCountryDetail(country)
        )
    }
}
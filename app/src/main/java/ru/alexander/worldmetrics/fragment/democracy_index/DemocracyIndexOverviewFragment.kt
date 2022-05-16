package ru.alexander.worldmetrics.fragment.democracy_index

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.fragment.democracy_index.DemocracyIndexOverviewFragmentDirections.Companion.actionDemocracyIndexOverviewFragmentToDemocracyIndexCountryDetailFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.alexander.worldmetrics.viewmodel.democracy_index.DemocracyIndexOverviewViewModel

class DemocracyIndexOverviewFragment : CountriesListWithIndexFragment() {
    override fun getData(): LiveData<Map<String, String>> {
        val model: DemocracyIndexOverviewViewModel by activityViewModels()
        return model.lastYearData
    }

    override fun onCountryClick(country: String) {
        navigateTo(
            findNavController(),
            actionDemocracyIndexOverviewFragmentToDemocracyIndexCountryDetailFragment(country)
        )
    }
}
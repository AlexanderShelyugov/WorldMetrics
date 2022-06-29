package ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.modules.democracy_index.viewmodel.DemocracyIndexOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model: DemocracyIndexOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<List<SimpleCountryValue>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun onCountryClick(country: String) {
        navigateTo(
            findNavController(),
            actionDemocracyIndexOverviewToDemocracyIndexCountryDetail(country)
        )
    }
}
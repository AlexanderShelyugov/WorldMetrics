package ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.fragment

import androidx.fragment.app.viewModels
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.fragment.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.viewmodel.DemocracyIndexOverviewViewModel

class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<DemocracyIndexOverviewViewModel>()

    override fun getData() = model.lastYearData
    override fun getValueRange() = model.getValueRange()
    override fun getNavigationOnClick(countryCode: String) =
        actionDemocracyIndexOverviewToDemocracyIndexCountryDetail(countryCode)
}
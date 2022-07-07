package ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.fragment.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.viewmodel.DemocracyIndexOverviewViewModel

@AndroidEntryPoint
class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<DemocracyIndexOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionDemocracyIndexOverviewToDemocracyIndexCountryDetail(countryCode)
}
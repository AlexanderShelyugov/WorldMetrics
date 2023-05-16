package ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.fragment.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.feature.index.politics.democracy_index.viewmodel.DemocracyIndexOverviewViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment.CountriesListWithIndexFragment

@AndroidEntryPoint
class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<DemocracyIndexOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionDemocracyIndexOverviewToDemocracyIndexCountryDetail(countryCode)
}
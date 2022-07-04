package ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment

import androidx.fragment.app.viewModels
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.modules.democracy_index.viewmodel.DemocracyIndexOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<DemocracyIndexOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun getNavigationOnClick(countryCode: String) =
        actionDemocracyIndexOverviewToDemocracyIndexCountryDetail(countryCode)
}
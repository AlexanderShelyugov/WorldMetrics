package ru.socialeducationapps.worldmetrics.modules.economics.gdp.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.fragment.GDPCountryListFragmentDirections.Companion.actionGDPCountryListToGDPCountryDetail
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.viewmodel.GDPOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountriesListWithIndexFragment

@AndroidEntryPoint
class GDPCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<GDPOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionGDPCountryListToGDPCountryDetail(countryCode)
}
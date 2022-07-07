package ru.socialeducationapps.worldmetrics.modules.demographics.population.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.modules.demographics.population.fragment.PopulationCountryListFragmentDirections.Companion.actionPopulationCountryListToPopulationDetail
import ru.socialeducationapps.worldmetrics.modules.demographics.population.viewmodel.PopulationOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountriesListWithIndexFragment

@AndroidEntryPoint
class PopulationCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<PopulationOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionPopulationCountryListToPopulationDetail(countryCode)
}
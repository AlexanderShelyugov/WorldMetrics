package ru.socialeducationapps.worldmetrics.modules.demographics.population.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.demographics.population.fragment.PopulationCountryListFragmentDirections.Companion.actionPopulationCountryListToPopulationDetail
import ru.socialeducationapps.worldmetrics.modules.demographics.population.viewmodel.PopulationOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

@AndroidEntryPoint
class PopulationCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<PopulationOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData
    override fun getValueRange() = model.getTotalPopulationRange()
    override fun getNavigationOnClick(countryCode: String) =
        actionPopulationCountryListToPopulationDetail(countryCode)
}
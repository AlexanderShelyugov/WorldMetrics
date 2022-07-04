package ru.socialeducationapps.worldmetrics.modules.economics.gdp.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.fragment.GDPCountryListFragmentDirections.Companion.actionGDPCountryListToGDPCountryDetail
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.viewmodel.GDPOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

@AndroidEntryPoint
class GDPCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<GDPOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData
    override fun getValueRange(): FeatureRange = model.getValueRange()
    override fun getNavigationOnClick(countryCode: String) =
        actionGDPCountryListToGDPCountryDetail(countryCode)
}
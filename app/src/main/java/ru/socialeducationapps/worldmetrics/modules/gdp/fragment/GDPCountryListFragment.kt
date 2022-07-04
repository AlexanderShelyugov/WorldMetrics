package ru.socialeducationapps.worldmetrics.modules.gdp.fragment

import androidx.fragment.app.viewModels
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.gdp.fragment.GDPCountryListFragmentDirections.Companion.actionGDPCountryListToGDPCountryDetail
import ru.socialeducationapps.worldmetrics.modules.gdp.viewmodel.GDPOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

class GDPCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<GDPOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData

    override fun getValueRange(): FeatureRange = model.getValueRange()

    override fun getNavigationOnClick(countryCode: String) =
        actionGDPCountryListToGDPCountryDetail(countryCode)
}
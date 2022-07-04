package ru.socialeducationapps.worldmetrics.modules.press_freedom.fragment

import androidx.fragment.app.viewModels
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.fragment.PressFreedomCountryListFragmentDirections.Companion.actionPressFreedomOverviewToPressFreedomCountryDetail
import ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel.PressFreedomOverviewViewModel

class PressFreedomCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<PressFreedomOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun getNavigationOnClick(countryCode: String) =
        actionPressFreedomOverviewToPressFreedomCountryDetail(countryCode)
}
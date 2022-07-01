package ru.socialeducationapps.worldmetrics.modules.press_freedom.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.global.NavigationHelper
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.fragment.PressFreedomCountryListFragmentDirections.Companion.actionPressFreedomOverviewToPressFreedomCountryDetail
import ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel.PressFreedomOverviewViewModel

class PressFreedomCountryListFragment : CountriesListWithIndexFragment() {
    private val model: PressFreedomOverviewViewModel by viewModels()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun onCountryClick(country: String) {
        NavigationHelper.navigateTo(
            findNavController(),
            actionPressFreedomOverviewToPressFreedomCountryDetail(country)
        )
    }
}
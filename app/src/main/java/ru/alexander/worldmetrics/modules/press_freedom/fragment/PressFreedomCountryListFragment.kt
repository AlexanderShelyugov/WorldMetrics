package ru.alexander.worldmetrics.modules.press_freedom.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.global.NavigationHelper
import ru.alexander.worldmetrics.modules.press_freedom.fragment.PressFreedomCountryListFragmentDirections.Companion.actionPressFreedomOverviewToPressFreedomCountryDetail
import ru.alexander.worldmetrics.modules.press_freedom.viewmodel.PressFreedomOverviewViewModel

class PressFreedomCountryListFragment : CountriesListWithIndexFragment() {
    private val model: PressFreedomOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<Map<String, String>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun onCountryClick(country: String) {
        NavigationHelper.navigateTo(
            findNavController(),
            actionPressFreedomOverviewToPressFreedomCountryDetail(country)
        )
    }
}
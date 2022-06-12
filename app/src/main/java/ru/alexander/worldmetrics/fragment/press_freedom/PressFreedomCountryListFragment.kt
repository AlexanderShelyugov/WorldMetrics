package ru.alexander.worldmetrics.fragment.press_freedom

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.fragment.press_freedom.PressFreedomCountryListFragmentDirections.Companion.actionPressFreedomOverviewFragmentToPressFreedomCountryDetailFragment
import ru.alexander.worldmetrics.global.NavigationHelper
import ru.alexander.worldmetrics.viewmodel.press_freedom.PressFreedomOverviewViewModel

class PressFreedomCountryListFragment : CountriesListWithIndexFragment() {
    private val model: PressFreedomOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<Map<String, String>> = model.lastYearData

    override fun getValueRange(): Pair<Float, Float> = model.getValueRange()

    override fun onCountryClick(country: String) {
        NavigationHelper.navigateTo(
            findNavController(),
            actionPressFreedomOverviewFragmentToPressFreedomCountryDetailFragment(country)
        )
    }
}
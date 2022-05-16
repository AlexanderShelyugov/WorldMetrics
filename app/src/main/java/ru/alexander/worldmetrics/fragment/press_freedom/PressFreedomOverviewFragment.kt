package ru.alexander.worldmetrics.fragment.press_freedom

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.fragment.press_freedom.PressFreedomOverviewFragmentDirections.Companion.actionPressFreedomOverviewFragmentToPressFreedomCountryDetailFragment
import ru.alexander.worldmetrics.global.NavigationHelper
import ru.alexander.worldmetrics.viewmodel.press_freedom.PressFreedomOverviewViewModel

class PressFreedomOverviewFragment : CountriesListWithIndexFragment() {
    override fun getData(): LiveData<Map<String, String>> {
        val model: PressFreedomOverviewViewModel by activityViewModels()
        return model.lastYearData
    }

    override fun onCountryClick(country: String) {
        NavigationHelper.navigateTo(
            findNavController(),
            actionPressFreedomOverviewFragmentToPressFreedomCountryDetailFragment(country)
        )
    }
}
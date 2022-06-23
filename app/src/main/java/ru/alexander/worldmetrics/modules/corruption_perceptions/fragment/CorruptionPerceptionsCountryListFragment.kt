package ru.alexander.worldmetrics.modules.corruption_perceptions.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.alexander.worldmetrics.modules.corruption_perceptions.fragment.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail
import ru.alexander.worldmetrics.modules.corruption_perceptions.viewmodel.CorruptionPerceptionsOverviewViewModel

class CorruptionPerceptionsCountryListFragment :
    CountriesListWithIndexFragment() {
    private val model: CorruptionPerceptionsOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<Map<String, String>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun onCountryClick(country: String) {
        navigateTo(
            findNavController(),
            actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail(
                country
            )
        )
    }

}
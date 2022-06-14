package ru.alexander.worldmetrics.corruption_perceptions.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.corruption_perceptions.fragment.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewFragmentToCorruptionPerceptionsCountryDetailFragment
import ru.alexander.worldmetrics.corruption_perceptions.viewmodel.CorruptionPerceptionsOverviewViewModel
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo

class CorruptionPerceptionsCountryListFragment :
    CountriesListWithIndexFragment() {
    private val model: CorruptionPerceptionsOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<Map<String, String>> = model.lastYearData

    override fun getValueRange(): Pair<Float, Float> = model.getValueRange()

    override fun onCountryClick(country: String) {
        navigateTo(
            findNavController(),
            actionCorruptionPerceptionsOverviewFragmentToCorruptionPerceptionsCountryDetailFragment(
                country
            )
        )
    }

}
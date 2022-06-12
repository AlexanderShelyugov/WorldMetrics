package ru.alexander.worldmetrics.fragment.corruption_perceptions

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.alexander.worldmetrics.fragment.corruption_perceptions.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewFragmentToCorruptionPerceptionsCountryDetailFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.alexander.worldmetrics.viewmodel.corruption_perceptions.CorruptionPerceptionsOverviewViewModel

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
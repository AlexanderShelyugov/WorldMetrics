package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.fragment.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel.CorruptionPerceptionsOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

class CorruptionPerceptionsCountryListFragment :
    CountriesListWithIndexFragment() {
    private val model: CorruptionPerceptionsOverviewViewModel by activityViewModels()

    override fun getData(): LiveData<List<SimpleCountryValue>> = model.lastYearData

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
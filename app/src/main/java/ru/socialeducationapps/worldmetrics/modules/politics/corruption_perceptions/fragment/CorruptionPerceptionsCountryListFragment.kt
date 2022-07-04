package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.fragment

import androidx.fragment.app.viewModels
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.fragment.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.viewmodel.CorruptionPerceptionsOverviewViewModel

class CorruptionPerceptionsCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<CorruptionPerceptionsOverviewViewModel>()

    override fun getData() = model.lastYearData
    override fun getValueRange() = model.getValueRange()
    override fun getNavigationOnClick(countryCode: String) =
        actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail(countryCode)
}
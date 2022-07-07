package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.fragment.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.viewmodel.CorruptionPerceptionsOverviewViewModel

@AndroidEntryPoint
class CorruptionPerceptionsCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<CorruptionPerceptionsOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail(countryCode)
}
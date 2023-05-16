package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.fragment.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.viewmodel.CorruptionPerceptionsOverviewViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment.CountriesListWithIndexFragment

@AndroidEntryPoint
class CorruptionPerceptionsCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<CorruptionPerceptionsOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail(countryCode)
}
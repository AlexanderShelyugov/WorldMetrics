package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.fragment.PressFreedomCountryListFragmentDirections.Companion.actionPressFreedomOverviewToPressFreedomCountryDetail
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.viewmodel.PressFreedomOverviewViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment.CountriesListWithIndexFragment

@AndroidEntryPoint
class PressFreedomCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<PressFreedomOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionPressFreedomOverviewToPressFreedomCountryDetail(countryCode)
}
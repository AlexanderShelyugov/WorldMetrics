package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.fragment.PressFreedomCountryListFragmentDirections.Companion.actionPressFreedomOverviewToPressFreedomCountryDetail
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.viewmodel.PressFreedomOverviewViewModel

@AndroidEntryPoint
class PressFreedomCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<PressFreedomOverviewViewModel>()
    override fun getOverviewViewModel() = model
    override fun getNavigationOnClick(countryCode: String) =
        actionPressFreedomOverviewToPressFreedomCountryDetail(countryCode)
}
package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.fragment

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.fragment.PressFreedomCountryListFragmentDirections.Companion.actionPressFreedomOverviewToPressFreedomCountryDetail
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.viewmodel.PressFreedomOverviewViewModel

@AndroidEntryPoint
class PressFreedomCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<PressFreedomOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData
    override fun getValueRange() = model.getValueRange()
    override fun getNavigationOnClick(countryCode: String) =
        actionPressFreedomOverviewToPressFreedomCountryDetail(countryCode)
}
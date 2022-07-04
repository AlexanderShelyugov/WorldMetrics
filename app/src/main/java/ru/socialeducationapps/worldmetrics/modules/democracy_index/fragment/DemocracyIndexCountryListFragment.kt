package ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment.DemocracyIndexCountryListFragmentDirections.Companion.actionDemocracyIndexOverviewToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.modules.democracy_index.viewmodel.DemocracyIndexOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

class DemocracyIndexCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<DemocracyIndexOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun onCountryClick(v: View, country: String) {
        val ctx = requireContext()
        val extras = FragmentNavigatorExtras(
            v to ctx.getString(R.string.transition_name_target_screen),
//            v.findViewById<View>(R.id.tv_country_name) to
//                    ctx.getString(R.string.transition_name_country_name_detail)
        )
        val action = actionDemocracyIndexOverviewToDemocracyIndexCountryDetail(country)
        findNavController().navigate(action, extras)
    }
}
package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.fragment.CountriesListWithIndexFragment
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.fragment.CorruptionPerceptionsCountryListFragmentDirections.Companion.actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel.CorruptionPerceptionsOverviewViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

class CorruptionPerceptionsCountryListFragment : CountriesListWithIndexFragment() {
    private val model by viewModels<CorruptionPerceptionsOverviewViewModel>()

    override fun getData(): Flow<List<SimpleCountryValue>> = model.lastYearData

    override fun getValueRange() = model.getValueRange()

    override fun onCountryClick(v: View, country: String) {
        findNavController().navigate(
            actionCorruptionPerceptionsOverviewToCorruptionPerceptionsCountryDetail(
                country
            )
        )
    }
}
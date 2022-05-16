package ru.alexander.worldmetrics.fragment.democracy_index

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.CountriesListWithIndexViewAdapter
import ru.alexander.worldmetrics.fragment.InjectableFragment
import ru.alexander.worldmetrics.fragment.democracy_index.DemocracyIndexOverviewFragmentDirections.Companion.actionDemocracyIndexOverviewFragmentToDemocracyIndexCountryDetailFragment
import ru.alexander.worldmetrics.model.KeyValueList
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.alexander.worldmetrics.viewmodel.DemocracyIndexOverviewViewModel

class DemocracyIndexOverviewFragment : InjectableFragment(R.layout.countries_list_democracy_index) {
    private val countriesAdapter = CountriesListWithIndexViewAdapter(this::onCountryClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rv_countries_list).run {
            adapter = countriesAdapter
        }

        val model: DemocracyIndexOverviewViewModel by activityViewModels()
        val observer = Observer<Map<String, String>> { countries ->
            countriesAdapter.setData(KeyValueList(countries))
        }
        model.lastYearData.observe(viewLifecycleOwner, observer)
    }

    private fun onCountryClick(country: String) {
        navigateTo(
            findNavController(),
            actionDemocracyIndexOverviewFragmentToDemocracyIndexCountryDetailFragment(country)
        )
    }
}
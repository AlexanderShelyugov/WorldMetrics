package ru.alexander.worldmetrics.modules.country_overview.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.modules.country_overview.adapter.CountryOverviewViewAdapter
import ru.alexander.worldmetrics.model.CountriesData.Companion.getNameByCode
import ru.alexander.worldmetrics.viewmodel.country_overview.CountryOverviewViewModel

class CountryOverviewFragment : Fragment(R.layout.country_overview) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: CountryOverviewFragmentArgs by navArgs()
        val model: CountryOverviewViewModel by activityViewModels()
        val countryCode = args.countryCode

        requireView().findViewById<TextView>(R.id.tv_country_name).text =
            getNameByCode(countryCode)

        val adapter = CountryOverviewViewAdapter()
        adapter.setData(model.getDataForCountry(countryCode))
        requireView().findViewById<RecyclerView>(R.id.rv_content).adapter = adapter
    }
}
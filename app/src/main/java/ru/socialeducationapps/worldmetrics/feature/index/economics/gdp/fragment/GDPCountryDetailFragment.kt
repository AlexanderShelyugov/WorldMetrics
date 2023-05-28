package ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.feature.index.economics.gdp.viewmodel.GDPCountryIndexDetailViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment.CountryIndexDetailFragment

class GDPCountryDetailFragment : CountryIndexDetailFragment<GDPValue>() {
    private val model by viewModels<GDPCountryIndexDetailViewModel>()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args by navArgs<GDPCountryDetailFragmentArgs>()
        return args.countryCode
    }
}
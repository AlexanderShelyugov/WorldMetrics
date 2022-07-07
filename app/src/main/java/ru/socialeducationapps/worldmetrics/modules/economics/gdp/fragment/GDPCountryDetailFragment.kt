package ru.socialeducationapps.worldmetrics.modules.economics.gdp.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.modules.economics.gdp.viewmodel.GDPCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountryIndexDetailFragment

class GDPCountryDetailFragment : CountryIndexDetailFragment<GDPValue>() {
    private val model by viewModels<GDPCountryDetailViewModel>()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args by navArgs<GDPCountryDetailFragmentArgs>()
        return args.countryCode
    }
}
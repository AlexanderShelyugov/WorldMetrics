package ru.socialeducationapps.worldmetrics.modules.gdp.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.gdp.model.GDPValue
import ru.socialeducationapps.worldmetrics.modules.gdp.rv_adapter.GDPAdapterFactory.Companion.getGDPAdapter
import ru.socialeducationapps.worldmetrics.modules.gdp.viewmodel.GDPCountryDetailViewModel

class GDPCountryDetailFragment : CountryIndexDetailFragment<GDPValue>() {
    private val model by viewModels<GDPCountryDetailViewModel>()

    override fun getCountryCode(): String {
        val args by navArgs<GDPCountryDetailFragmentArgs>()
        return args.countryCode
    }
    override fun getData() = model
        .apply { setCountry(getCountryCode()) }
        .run { allData }
    override fun getAdapter(): IndexFeaturesRVAdapter<GDPValue> = getGDPAdapter()
    override fun getFeatureRanges() = model.getFeatureRanges(getCountryCode())
}
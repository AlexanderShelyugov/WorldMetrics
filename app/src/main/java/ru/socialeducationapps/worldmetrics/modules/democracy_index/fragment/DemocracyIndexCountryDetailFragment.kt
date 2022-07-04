package ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.democracy_index.rv_adapter.DemocracyIndexAdapterFactory.Companion.getDemocracyIndexFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.democracy_index.viewmodel.DemocracyIndexCountryDetailViewModel

class DemocracyIndexCountryDetailFragment :
    CountryIndexDetailFragment<DemocracyIndexValue>() {
    private val model: DemocracyIndexCountryDetailViewModel by viewModels()
    override fun getCountryCode(): String {
        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }

    override fun getData() = model
        .apply { setCountry(getCountryCode()) }
        .allData

    override fun getAdapter(): IndexFeaturesRVAdapter<DemocracyIndexValue> =
        getDemocracyIndexFeaturesAdapter()

    override fun getFeatureRanges() = model.getFeatureRanges(getCountryCode())
}
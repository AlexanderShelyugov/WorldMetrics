package ru.socialeducationapps.worldmetrics.modules.democracy_index.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.democracy_index.rv_adapter.DemocracyIndexAdapterFactory.Companion.getDemocracyIndexFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.democracy_index.viewmodel.DemocracyIndexCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange

class DemocracyIndexCountryDetailFragment :
    CountryIndexDetailFragment<DemocracyIndexValue>() {
    private val model: DemocracyIndexCountryDetailViewModel by viewModels()
    override fun getCountryCode(): String {
        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }

    override fun getData(): Flow<List<DemocracyIndexValue>> {
        model.setCountry(getCountryCode())
        return model.allData
    }

    override fun getAdapter(): IndexFeaturesRVAdapter<DemocracyIndexValue> =
        getDemocracyIndexFeaturesAdapter()

    override fun getFeatureRanges(): List<FeatureRange> {
        return model.getFeatureRanges(getCountryCode())
    }
}
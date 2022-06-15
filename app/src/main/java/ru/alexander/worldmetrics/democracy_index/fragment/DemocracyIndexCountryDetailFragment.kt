package ru.alexander.worldmetrics.democracy_index.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.democracy_index.adapter.DemocracyIndexAdapter
import ru.alexander.worldmetrics.fragment.CountryIndexDetailFragment
import ru.alexander.worldmetrics.democracy_index.model.DemocracyIndexValue
import ru.alexander.worldmetrics.democracy_index.viewmodel.DemocracyIndexCountryDetailViewModel

class DemocracyIndexCountryDetailFragment :
    CountryIndexDetailFragment<DemocracyIndexValue>() {
    override fun getCountryCode(): String {
        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }

    override fun getData(): LiveData<List<DemocracyIndexValue>> {
        val model: DemocracyIndexCountryDetailViewModel by activityViewModels()
        model.setCountry(getCountryCode())
        return model.allData
    }

    override fun getAdapter(): IndexFeaturesRVAdapter<DemocracyIndexValue> =
        DemocracyIndexAdapter()
}
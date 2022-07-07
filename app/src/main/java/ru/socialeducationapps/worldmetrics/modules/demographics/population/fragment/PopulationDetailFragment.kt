package ru.socialeducationapps.worldmetrics.modules.demographics.population.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.modules.demographics.population.rv_adapter.PopulationAdapterFactory.Companion.getPopulationAdapter
import ru.socialeducationapps.worldmetrics.modules.demographics.population.viewmodel.PopulationCountryDetailViewModel

class PopulationDetailFragment : CountryIndexDetailFragment<PopulationIndexValue>() {
    private val model by viewModels<PopulationCountryDetailViewModel>()

    override fun getCountryCode(): String {
        val args by navArgs<PopulationDetailFragmentArgs>()
        return args.iso3CountryCode
    }

    override fun getData() = model
        .apply { setCountry(getCountryCode()) }
        .run { allData }

    override fun getAdapter(): IndexFeaturesRVAdapter<PopulationIndexValue> = getPopulationAdapter()
    override fun getFeatureRanges() = model.getFeatureRanges()
}
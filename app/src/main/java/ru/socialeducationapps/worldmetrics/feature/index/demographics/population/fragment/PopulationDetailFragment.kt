package ru.socialeducationapps.worldmetrics.feature.index.demographics.population.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.feature.index.demographics.population.viewmodel.PopulationCountryIndexDetailViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment.CountryIndexDetailFragment

class PopulationDetailFragment : CountryIndexDetailFragment<PopulationIndexValue>() {
    private val model by viewModels<PopulationCountryIndexDetailViewModel>()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args by navArgs<PopulationDetailFragmentArgs>()
        return args.iso3CountryCode
    }
}
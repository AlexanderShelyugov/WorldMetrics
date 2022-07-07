package ru.socialeducationapps.worldmetrics.modules.demographics.population.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.modules.demographics.population.model.PopulationIndexValue
import ru.socialeducationapps.worldmetrics.modules.demographics.population.viewmodel.PopulationCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountryIndexDetailFragment

class PopulationDetailFragment : CountryIndexDetailFragment<PopulationIndexValue>() {
    private val model by viewModels<PopulationCountryDetailViewModel>()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args by navArgs<PopulationDetailFragmentArgs>()
        return args.iso3CountryCode
    }
}
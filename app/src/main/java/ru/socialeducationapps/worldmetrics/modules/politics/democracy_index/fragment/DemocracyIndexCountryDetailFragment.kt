package ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.model.DemocracyIndexValue
import ru.socialeducationapps.worldmetrics.modules.politics.democracy_index.viewmodel.DemocracyIndexCountryDetailViewModel

class DemocracyIndexCountryDetailFragment :
    CountryIndexDetailFragment<DemocracyIndexValue>() {
    private val model: DemocracyIndexCountryDetailViewModel by viewModels()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args: DemocracyIndexCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }
}
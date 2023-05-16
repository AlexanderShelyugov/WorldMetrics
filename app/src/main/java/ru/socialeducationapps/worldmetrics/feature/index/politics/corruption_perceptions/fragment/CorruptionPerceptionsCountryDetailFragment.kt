package ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.corruption_perceptions.viewmodel.CorruptionPerceptionsCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment.CountryIndexDetailFragment

class CorruptionPerceptionsCountryDetailFragment :
    CountryIndexDetailFragment<CorruptionPerceptionsValue>() {
    private val model: CorruptionPerceptionsCountryDetailViewModel by viewModels()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args by navArgs<CorruptionPerceptionsCountryDetailFragmentArgs>()
        return args.countryCode
    }
}

package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.viewmodel.CorruptionPerceptionsCountryDetailViewModel

class CorruptionPerceptionsCountryDetailFragment :
    CountryIndexDetailFragment<CorruptionPerceptionsValue>() {
    private val model: CorruptionPerceptionsCountryDetailViewModel by viewModels()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args by navArgs<CorruptionPerceptionsCountryDetailFragmentArgs>()
        return args.countryCode
    }
}

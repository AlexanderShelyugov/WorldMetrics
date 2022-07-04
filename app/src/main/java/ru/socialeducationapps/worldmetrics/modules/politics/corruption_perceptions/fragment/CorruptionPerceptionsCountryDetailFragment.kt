package ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.rv_adapter.CorruptionPerceptionsAdapterFactory.Companion.getCorruptionPerceptionsFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.politics.corruption_perceptions.viewmodel.CorruptionPerceptionsCountryDetailViewModel

class CorruptionPerceptionsCountryDetailFragment :
    CountryIndexDetailFragment<CorruptionPerceptionsValue>() {
    private val model: CorruptionPerceptionsCountryDetailViewModel by viewModels()

    override fun getCountryCode(): String {
        val args by navArgs<CorruptionPerceptionsCountryDetailFragmentArgs>()
        return args.countryCode
    }

    override fun getData() = model
        .apply { setCountry(getCountryCode()) }
        .allData
    override fun getAdapter(): IndexFeaturesRVAdapter<CorruptionPerceptionsValue> =
        getCorruptionPerceptionsFeaturesAdapter()
    override fun getFeatureRanges() = model.getFeatureRanges(getCountryCode())
}

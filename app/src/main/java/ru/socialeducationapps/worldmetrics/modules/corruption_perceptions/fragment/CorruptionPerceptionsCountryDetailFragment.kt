package ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.rv_adapter.CorruptionPerceptionsAdapterFactory.Companion.getCorruptionPerceptionsFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.corruption_perceptions.viewmodel.CorruptionPerceptionsCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange

class CorruptionPerceptionsCountryDetailFragment :
    CountryIndexDetailFragment<CorruptionPerceptionsValue>() {
    private val model: CorruptionPerceptionsCountryDetailViewModel by viewModels()

    override fun getCountryCode(): String {
        val args by navArgs<CorruptionPerceptionsCountryDetailFragmentArgs>()
        return args.countryCode
    }

    override fun getData(): Flow<List<CorruptionPerceptionsValue>> =
        model
            .apply { setCountry(getCountryCode()) }
            .allData

    override fun getAdapter(): IndexFeaturesRVAdapter<CorruptionPerceptionsValue> =
        getCorruptionPerceptionsFeaturesAdapter()

    override fun getFeatureRanges(): List<FeatureRange> {
        return model.getFeatureRanges(getCountryCode())
    }
}

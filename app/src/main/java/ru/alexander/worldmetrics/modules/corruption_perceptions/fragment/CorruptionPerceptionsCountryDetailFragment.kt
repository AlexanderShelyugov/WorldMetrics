package ru.alexander.worldmetrics.modules.corruption_perceptions.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.fragment.CountryIndexDetailFragment
import ru.alexander.worldmetrics.modules.corruption_perceptions.model.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.modules.corruption_perceptions.rv_adapter.CorruptionPerceptionsAdapterFactory.Companion.getCorruptionPerceptionsFeaturesAdapter
import ru.alexander.worldmetrics.modules.corruption_perceptions.viewmodel.CorruptionPerceptionsCountryDetailViewModel

class CorruptionPerceptionsCountryDetailFragment :
    CountryIndexDetailFragment<CorruptionPerceptionsValue>() {
    override fun getCountryCode(): String {
        val args: CorruptionPerceptionsCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }

    override fun getData(): LiveData<List<CorruptionPerceptionsValue>> {
        val model: CorruptionPerceptionsCountryDetailViewModel by activityViewModels()
        model.setCountry(getCountryCode())
        return model.allData
    }

    override fun getAdapter(): IndexFeaturesRVAdapter<CorruptionPerceptionsValue> =
        getCorruptionPerceptionsFeaturesAdapter()
}

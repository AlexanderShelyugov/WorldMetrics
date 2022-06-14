package ru.alexander.worldmetrics.fragment.corruption_perceptions

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.adapter.corruption_perceptions.CorruptionPerceptionsAdapter
import ru.alexander.worldmetrics.fragment.CountryIndexDetailFragment
import ru.alexander.worldmetrics.model.corruption_perceptions.CorruptionPerceptionsValue
import ru.alexander.worldmetrics.viewmodel.corruption_perceptions.CorruptionPerceptionsCountryDetailViewModel

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
        CorruptionPerceptionsAdapter()
}

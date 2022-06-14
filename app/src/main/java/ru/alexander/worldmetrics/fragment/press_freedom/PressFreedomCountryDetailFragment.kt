package ru.alexander.worldmetrics.fragment.press_freedom

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.adapter.press_freedom.PressFreedomAdapter
import ru.alexander.worldmetrics.fragment.CountryIndexDetailFragment
import ru.alexander.worldmetrics.model.press_freedom.PressFreedomValue
import ru.alexander.worldmetrics.viewmodel.press_freedom.PressFreedomCountryDetailViewModel

class PressFreedomCountryDetailFragment :
    CountryIndexDetailFragment<PressFreedomValue>() {
    override fun getCountryCode(): String {
        val args: PressFreedomCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }

    override fun getData(): LiveData<List<PressFreedomValue>> {
        val model: PressFreedomCountryDetailViewModel by activityViewModels()
        model.setCountry(getCountryCode())
        return model.allData
    }

    override fun getAdapter(): IndexFeaturesRVAdapter<PressFreedomValue> =
        PressFreedomAdapter()
}
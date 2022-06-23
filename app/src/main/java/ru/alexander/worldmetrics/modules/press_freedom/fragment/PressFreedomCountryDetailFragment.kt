package ru.alexander.worldmetrics.modules.press_freedom.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.alexander.worldmetrics.fragment.CountryIndexDetailFragment
import ru.alexander.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.alexander.worldmetrics.modules.press_freedom.rv_adapter.PressFreedomAdapterFactory.Companion.getPressFreedomFeaturesAdapter
import ru.alexander.worldmetrics.modules.press_freedom.viewmodel.PressFreedomCountryDetailViewModel

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
        getPressFreedomFeaturesAdapter()

    override fun getFeatureColors(): List<Int> {
        val model: PressFreedomCountryDetailViewModel by activityViewModels()
        return model.getFeatureColors(getCountryCode())
    }
}
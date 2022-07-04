package ru.socialeducationapps.worldmetrics.modules.press_freedom.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.rv_adapter.PressFreedomAdapterFactory.Companion.getPressFreedomFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel.PressFreedomCountryDetailViewModel

class PressFreedomCountryDetailFragment :
    CountryIndexDetailFragment<PressFreedomValue>() {
    private val model: PressFreedomCountryDetailViewModel by viewModels()

    override fun getCountryCode(): String {
        val args: PressFreedomCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }
    override fun getData() = model
        .apply { setCountry(getCountryCode()) }
        .allData
    override fun getAdapter(): IndexFeaturesRVAdapter<PressFreedomValue> =
        getPressFreedomFeaturesAdapter()
    override fun getFeatureRanges() = model.getFeatureRanges(getCountryCode())
}
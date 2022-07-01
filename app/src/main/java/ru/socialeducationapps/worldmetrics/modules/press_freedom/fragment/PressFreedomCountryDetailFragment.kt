package ru.socialeducationapps.worldmetrics.modules.press_freedom.fragment

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.Flow
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.modules.press_freedom.rv_adapter.PressFreedomAdapterFactory.Companion.getPressFreedomFeaturesAdapter
import ru.socialeducationapps.worldmetrics.modules.press_freedom.viewmodel.PressFreedomCountryDetailViewModel

class PressFreedomCountryDetailFragment :
    CountryIndexDetailFragment<PressFreedomValue>() {
    override fun getCountryCode(): String {
        val args: PressFreedomCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }

    override fun getData(): Flow<List<PressFreedomValue>> {
        val model: PressFreedomCountryDetailViewModel by activityViewModels()
        model.setCountry(getCountryCode())
        return model.allData
    }

    override fun getAdapter(): IndexFeaturesRVAdapter<PressFreedomValue> =
        getPressFreedomFeaturesAdapter()

    override fun getFeatureRanges(): List<FeatureRange> {
        val model: PressFreedomCountryDetailViewModel by activityViewModels()
        return model.getFeatureRanges(getCountryCode())
    }
}
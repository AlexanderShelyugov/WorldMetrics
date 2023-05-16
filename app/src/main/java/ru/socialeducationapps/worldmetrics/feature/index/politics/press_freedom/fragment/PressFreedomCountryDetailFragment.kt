package ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.feature.index.politics.press_freedom.viewmodel.PressFreedomCountryDetailViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment.CountryIndexDetailFragment

class PressFreedomCountryDetailFragment :
    CountryIndexDetailFragment<PressFreedomValue>() {
    private val model: PressFreedomCountryDetailViewModel by viewModels()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args: PressFreedomCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }
}
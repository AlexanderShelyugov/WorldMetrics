package ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.socialeducationapps.worldmetrics.modules.indexes.fragment.CountryIndexDetailFragment
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.model.PressFreedomValue
import ru.socialeducationapps.worldmetrics.modules.politics.press_freedom.viewmodel.PressFreedomCountryDetailViewModel

class PressFreedomCountryDetailFragment :
    CountryIndexDetailFragment<PressFreedomValue>() {
    private val model: PressFreedomCountryDetailViewModel by viewModels()
    override fun getCountryDetailViewModel() = model
    override fun getCountryCode(): String {
        val args: PressFreedomCountryDetailFragmentArgs by navArgs()
        return args.countryCode
    }
}
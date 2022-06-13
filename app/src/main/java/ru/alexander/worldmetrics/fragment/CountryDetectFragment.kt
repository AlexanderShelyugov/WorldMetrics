package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.CountriesData
import ru.alexander.worldmetrics.viewmodel.CurrentCountryViewModel

class CountryDetectFragment : InjectableFragment(R.layout.country_detect_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: CurrentCountryViewModel by activityViewModels()
        requireView().findViewById<View>(R.id.b_confirm).setOnClickListener {
            val countryCode = CountriesData.CODES_TO_NAMES.keys.random()
            model.setCurrentCountryCode(countryCode)
            findNavController().navigateUp()
        }
    }
}
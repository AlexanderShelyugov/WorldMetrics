package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.corruption_perceptions.CorruptionPerceptionsCountryDetailFragmentArgs
import ru.alexander.worldmetrics.model.CountriesData.Companion.getNameByCode

class CountryOverviewFragment : Fragment(R.layout.country_overview) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: CorruptionPerceptionsCountryDetailFragmentArgs by navArgs()
        val countryCode = args.countryCode

        requireView().findViewById<TextView>(R.id.tv_country_name).text =
            getNameByCode(countryCode)
    }
}
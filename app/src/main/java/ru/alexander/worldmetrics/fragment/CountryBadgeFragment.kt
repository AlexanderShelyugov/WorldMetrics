package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenFragmentToCountryDetectFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.bindNavigation
import ru.alexander.worldmetrics.model.CountriesData
import ru.alexander.worldmetrics.viewmodel.CurrentCountryViewModel

class CountryBadgeFragment : InjectableFragment(R.layout.country_badge_fragment) {
    private lateinit var countryInfo: TextView
    private lateinit var messageLabel: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().run {
            countryInfo = findViewById(R.id.tv_country_name)
            messageLabel = findViewById(R.id.tv_additional_message)
        }

        val model: CurrentCountryViewModel by activityViewModels()

        model.currentCountryCode.observe(viewLifecycleOwner, this::setCountryCode)
        bindNavigation(
            requireView().findViewById(R.id.ib_country_search_wizard),
            actionHomeScreenFragmentToCountryDetectFragment()
        )
    }

    private fun setCountryCode(countryCode: String) {
        val countryName: String
        messageLabel.run {
            if (countryCode.isBlank()) {
                countryName = getString(R.string.country_name_unknown)
                text = getString(R.string.consider_searching_your_country)
                visibility = VISIBLE
            } else {
                countryName = CountriesData.getNameByCode(countryCode)
                text = ""
                visibility = GONE
            }
        }
        countryInfo.text = countryName
    }
}
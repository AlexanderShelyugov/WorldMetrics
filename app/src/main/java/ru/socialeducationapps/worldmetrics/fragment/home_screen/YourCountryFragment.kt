package ru.socialeducationapps.worldmetrics.fragment.home_screen

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToCountryDetect
import ru.socialeducationapps.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToCountryOverview
import ru.socialeducationapps.worldmetrics.global.NavigationHelper.Companion.bindNavigation
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.getNameByCode
import ru.socialeducationapps.worldmetrics.viewmodel.CurrentCountryViewModel

class YourCountryFragment : Fragment(R.layout.your_country_fragment) {
    private lateinit var locationMarker: ImageView
    private lateinit var countryInfo: TextView
    private lateinit var messageLabel: TextView
    private lateinit var countryDetect: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val v = requireView()
        v.run {
            locationMarker = findViewById(R.id.iv_location_marker)
            countryInfo = findViewById(R.id.tv_country_name)
            messageLabel = findViewById(R.id.tv_additional_message)
            countryDetect = findViewById(R.id.ib_country_search_wizard)
        }

        val countryBadge: View = v.findViewById(R.id.cl_country_badge)
        val model: CurrentCountryViewModel by activityViewModels()
        model.currentCountryCode.observe(viewLifecycleOwner) { countryCode ->
            countryBadge.isClickable = countryCode.isNotBlank()
            setCountryCode(countryCode)
        }
        bindNavigation(
            countryBadge,
            actionHomeScreenToCountryOverview(model.currentCountryCode.value!!)
        )
        bindNavigation(
            v.findViewById(R.id.ib_country_search_wizard),
            actionHomeScreenToCountryDetect()
        )
    }

    private fun setCountryCode(countryCode: String) {
        val countryName: String
        if (countryCode.isBlank()) {
            countryName = getString(R.string.country_name_unknown)
            locationMarker.setImageResource(R.drawable.geolocation_unknown_marker)
            messageLabel.run {
                text = getString(R.string.consider_searching_your_country)
                visibility = VISIBLE
            }
            countryDetect.setImageResource(R.drawable.action_wizard)
        } else {
            countryName = getNameByCode(countryCode)
            locationMarker.setImageResource(R.drawable.geolocation_marker)
            messageLabel.run {
                text = ""
                visibility = GONE
            }
            countryDetect.setImageResource(R.drawable.edit_icon)
        }
        countryInfo.text = countryName
    }
}
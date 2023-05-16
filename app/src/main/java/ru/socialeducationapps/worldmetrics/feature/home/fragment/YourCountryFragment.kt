package ru.socialeducationapps.worldmetrics.feature.home.fragment

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
import ru.socialeducationapps.worldmetrics.feature.country.current.viewmodel.CurrentCountryViewModel
import ru.socialeducationapps.worldmetrics.feature.helper.utils.NavigationHelper.Companion.bindNavigation
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToCountryDetect
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode

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

        val model: CurrentCountryViewModel by activityViewModels()
        model.currentCountryCode.observe(viewLifecycleOwner) { countryCode ->
            setCountryCode(countryCode)
        }
        bindNavigation(
            v.findViewById(R.id.ib_country_search_wizard),
            actionHomeScreenToCountryDetect()
        )
    }

    private fun setCountryCode(countryCode: String) {
        var countryNameId = R.string.country_name_unknown
        if (countryCode.isBlank()) {
            locationMarker.setImageResource(R.drawable.geolocation_unknown_marker)
            messageLabel.run {
                text = getString(R.string.consider_searching_your_country)
                visibility = VISIBLE
            }
            countryDetect.setImageResource(R.drawable.search_icon)
        } else {
            getNameIdByCode(countryCode)?.let { countryNameId = it }
            locationMarker.setImageResource(R.drawable.geolocation_marker)
            messageLabel.run {
                text = ""
                visibility = GONE
            }
            countryDetect.setImageResource(R.drawable.edit_icon)
        }
        countryInfo.setText(countryNameId)
    }
}
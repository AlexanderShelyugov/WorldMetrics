package ru.alexander.worldmetrics.fragment

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.model.CountriesData
import ru.alexander.worldmetrics.model.CountriesData.Companion.getNameByCode
import ru.alexander.worldmetrics.viewmodel.CurrentCountryViewModel


class CountryDetectFragment : Fragment(R.layout.country_detect_fragment) {
    private lateinit var countryCode: String
    private lateinit var content: TextView
    private lateinit var lastAction: () -> Unit
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                lastAction.invoke()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                setCountryCode("")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: CurrentCountryViewModel by activityViewModels()
        val v = requireView()
        content = v.findViewById(R.id.tv_content)
        v.findViewById<View>(R.id.b_confirm).setOnClickListener {
            model.setCurrentCountryCode(countryCode)
            findNavController().navigateUp()
        }
        v.findViewById<View>(R.id.fl_detect_by_gps).setOnClickListener {
            tryDetectByGPS {
                detectCountryBy(GPS_PROVIDER, this::setCountryCode)
            }
        }
        v.findViewById<View>(R.id.fl_detect_by_network).setOnClickListener {
            tryDetectByNetwork {
                detectCountryBy(NETWORK_PROVIDER, this::setCountryCode)
            }
        }
    }

    private fun tryDetectByGPS(action: () -> Unit) {
        val permission = ACCESS_FINE_LOCATION
        lastAction = action
        when {
            checkSelfPermission(requireContext(), permission) == PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                lastAction.invoke()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Allow GPS access?")
                    .setMessage("We need your permission to locate country")
                    .setCancelable(true)
                    .setPositiveButton("Yes") { _, _ ->
                        tryDetectByGPS(action)
                    }
                    .setNegativeButton("No") { _, _ ->
                        setCountryCode("")
                    }
                    .setIcon(R.drawable.geolocation_unknown_marker)
                    .show()
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissionLauncher.launch(permission)
            }
        }
    }

    private fun tryDetectByNetwork(action: () -> Unit) {
        val permission = ACCESS_COARSE_LOCATION
        lastAction = action
        when {
            checkSelfPermission(requireContext(), permission) == PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                lastAction.invoke()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Allow GPS access?")
                    .setMessage("We need your permission to locate country")
                    .setCancelable(true)
                    .setPositiveButton("Yes") { _, _ ->
                        tryDetectByGPS(action)
                    }
                    .setNegativeButton("No", null)
                    .setIcon(R.drawable.geolocation_unknown_marker)
                    .show()
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissionLauncher.launch(permission)
            }
        }
    }

    private fun detectCountryBy(provider: String, countryConsumer: (String) -> Unit) {
        val lm = getLocationManager()
        lm.getCurrentLocation(provider, null, requireContext().mainExecutor) {
            val countryCode = countryCodeFromLocation(it)
            countryConsumer.invoke(countryCode)
        }
    }

    private fun countryCodeFromLocation(location: Location): String {
        val geocoder = Geocoder(requireContext())
        val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)[0]
        return address.countryCode
    }

    private fun setCountryCode(alpha2Code: String) {
        countryCode = CountriesData.getAlpha3Code(alpha2Code)
        val message =
            if (countryCode.isBlank()) getString(R.string.failed_to_detect_country)
            else getString(R.string.question_is_your_country) + " - " + getNameByCode(countryCode) + "?"
        content.text = message
    }

    private fun getLocationManager() =
        requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
}
package ru.socialeducationapps.worldmetrics.fragment

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager.beginDelayedTransition
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.transition.addListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.CountriesListAdapter
import ru.socialeducationapps.worldmetrics.adapter.CountryListAdapterItem
import ru.socialeducationapps.worldmetrics.adapter.ScrollToTopOnChangeObserver
import ru.socialeducationapps.worldmetrics.fragment.CountryDetectFragment.FragmentState.COUNTRIES_LIST
import ru.socialeducationapps.worldmetrics.fragment.CountryDetectFragment.FragmentState.DETECTION_TYPES
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.getAllCountryCodes
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.getAlpha2Code
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.getAlpha3Code
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.viewmodel.CurrentCountryViewModel
import java.util.concurrent.TimeUnit.SECONDS


class CountryDetectFragment : Fragment(R.layout.country_detect_fragment) {
    private lateinit var countryCode: String
    private lateinit var countrySuggestionContainer: ViewGroup
    private lateinit var content: TextView
    private lateinit var confirm: View
    private lateinit var viewDetectionTypes: ViewGroup
    private lateinit var viewCountrySearch: ViewGroup
    private lateinit var viewCountriesList: RecyclerView

    private lateinit var lastAction: () -> Unit
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var countriesListAdapter: CountriesListAdapter
    private var fragmentState: FragmentState = DETECTION_TYPES

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                lastAction()
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
        requireView().run {
            countrySuggestionContainer = findViewById(R.id.ll_country_suggestion)
            content = findViewById(R.id.tv_content)
            confirm = findViewById<View>(R.id.ib_confirm).also {
                it.setOnClickListener {
                    model.setCurrentCountryCode(countryCode)
                    findNavController().navigateUp()
                }
            }
            viewDetectionTypes = findViewById(R.id.ll_detection_type)
            viewCountrySearch = findViewById<ViewGroup>(R.id.ll_country_search)
                .also { viewCountriesList = it.findViewById(R.id.rv_countries_list) }
            findViewById<View>(R.id.fl_detect_by_gps).setOnClickListener {
                tryDetectByGPS {
                    detectCountryBy(GPS_PROVIDER, this@CountryDetectFragment::setCountryCode)
                }
            }
            findViewById<View>(R.id.fl_detect_by_network).setOnClickListener {
                tryDetectByNetwork {
                    detectCountryBy(NETWORK_PROVIDER, this@CountryDetectFragment::setCountryCode)
                }
            }
            findViewById<View>(R.id.fl_detect_manual).setOnClickListener {
                switchFragmentState(COUNTRIES_LIST)
            }
        }
        initCountriesList()
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    when (fragmentState) {
                        DETECTION_TYPES ->
                            findNavController().navigateUp()
                        else ->
                            switchFragmentState(DETECTION_TYPES)
                    }
                }
            })
        switchFragmentState(fragmentState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (fragmentState == COUNTRIES_LIST) {
            inflater.inflate(R.menu.action_bar_country_list, menu)
            (menu.findItem(R.id.action_search).actionView as SearchView)
                .setOnQueryTextListener(onSearchListener)
            listOf(R.id.action_sort_type, R.id.action_sort_order).forEach {
                menu.findItem(it)?.isVisible = false
            }
        }
    }

    private fun tryDetectByGPS(action: () -> Unit) =
        tryRequestPermission(ACCESS_FINE_LOCATION, R.string.question_allow_gps_access, action)

    private fun tryDetectByNetwork(action: () -> Unit) =
        tryRequestPermission(ACCESS_COARSE_LOCATION, R.string.question_allow_network_access, action)

    private fun tryRequestPermission(
        permission: String,
        dialogRequestMessage: Int,
        action: () -> Unit,
    ) {
        val ctx = requireContext()
        lastAction = action
        when {
            checkSelfPermission(ctx, permission) == PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                lastAction.invoke()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                MaterialAlertDialogBuilder(ctx)
                    .setTitle(dialogRequestMessage)
                    .setMessage(R.string.country_detection_permission_explanation)
                    .setCancelable(true)
                    .setPositiveButton(R.string.yes) { _, _ ->
                        tryRequestPermission(permission, dialogRequestMessage, action)
                    }
                    .setNegativeButton(R.string.no, null)
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
        getLocationManager().getCurrentLocation(provider, null, requireContext().mainExecutor) {
            val countryCode = it?.run(this::countryCodeFromLocation) ?: ""
            countryConsumer(countryCode)
        }
    }

    private fun countryCodeFromLocation(location: Location): String {
        val geocoder = Geocoder(requireContext())
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
            ?.takeIf { it.isNotEmpty<Address?>() }
            ?.first()
            ?.countryCode ?: ""
    }

    private fun setCountryCode(alpha2Code: String) {
        countryCode = getAlpha3Code(alpha2Code)
        val message = countryCode
            .takeIf { it.isNotBlank() }
            ?.let {
                getNameIdByCode(it)
                    ?.run(requireContext()::getString)
            }
            ?.let { getString(R.string.question_is_your_country, it) }
            ?: getString(R.string.failed_to_detect_country)
        confirm.isVisible = countryCode.isNotBlank()
        content.text = message
        content.isSelected = false
        if (countrySuggestionContainer.isVisible) {
            content.postDelayed({
                content.isSelected = true
            }, SECONDS.toMillis(1))
            return
        }
        beginDelayedTransition(countrySuggestionContainer, Slide(Gravity.TOP).also {
            it.addListener(onEnd = {
                content.postDelayed({
                    content.isSelected = true
                }, SECONDS.toMillis(1))
            })
        })
        countrySuggestionContainer.isVisible = true
    }

    private fun switchFragmentState(state: FragmentState) {
        fragmentState = state
        val transitionOut = Fade()
        val transitionIn = Fade()
            .apply { startDelay = transitionOut.duration / 2 }
        val outgoingView =
            if (fragmentState == DETECTION_TYPES) viewCountrySearch else viewDetectionTypes
        val incomingView =
            if (fragmentState == DETECTION_TYPES) viewDetectionTypes else viewCountrySearch

        beginDelayedTransition(outgoingView, transitionOut)
        beginDelayedTransition(incomingView, transitionIn)
        outgoingView.isVisible = false
        incomingView.isVisible = true
        setHasOptionsMenu(fragmentState == COUNTRIES_LIST)
        requireActivity().invalidateOptionsMenu()
    }

    private fun initCountriesList() {
        val ctx = requireContext()
        countriesListAdapter = CountriesListAdapter()
        countriesListAdapter.registerAdapterDataObserver(
            ScrollToTopOnChangeObserver(viewCountriesList)
        )
        getAllCountryCodes().asSequence()
            .map { it to getNameIdByCode(it) }
            .filter { it.second != null }
            .map {
                CountryListAdapterItem(it.first, ctx.getString(it.second!!)) { iso3Code ->
                    callback(getAlpha2Code(iso3Code))
                }
            }
            .toList()
            .run(countriesListAdapter::setData)
        viewCountriesList.adapter = countriesListAdapter
    }

    private fun getLocationManager() =
        requireContext().getSystemService(LOCATION_SERVICE) as LocationManager

    private val callback: (String) -> Unit = { countryCode ->
        setCountryCode(countryCode)
        switchFragmentState(DETECTION_TYPES)
    }

    private enum class FragmentState {
        DETECTION_TYPES,
        COUNTRIES_LIST,
    }

    private val onSearchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            countriesListAdapter.searchWith(query ?: "")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean = onQueryTextSubmit(newText)
    }
}
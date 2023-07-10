package ru.socialeducationapps.worldmetrics.feature.countries_comparison.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.countries_comparison.viewmodel.CountriesComparisonViewModel
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings

class CountriesComparisonFragment : InjectableFragment(R.layout.comparison_screen) {
    private val model by viewModels<CountriesComparisonViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args by navArgs<CountriesComparisonFragmentArgs>()
        val wholeView = requireView()
        val countryASelectionArea: View = wholeView.findViewById(R.id.clCountrySelectA)
        val countryBSelectionArea: View = wholeView.findViewById(R.id.clCountrySelectB)
        val countryALabel: TextView = countryASelectionArea.findViewById(R.id.tvLabelA)
        val countryBLabel: TextView = countryBSelectionArea.findViewById(R.id.tvLabelB)
        val comparisonRV: RecyclerView = wholeView.findViewById(R.id.rvCountriesComparison)

        val extractCountryName = { countryCode: String ->
            requireContext().getText(
                CountryResourceBindings.getNameIdByCode(countryCode)
                    ?: R.string.country_name_unknown
            )
        }

        model.setCountryA(args.countryACode)
        model.setCountryB(args.countryBCode)

        lifecycleScope.launch {
            model.getViewState().collect {
            }
        }
        countryALabel.text = extractCountryName(args.countryACode)
        countryBLabel.text = extractCountryName(args.countryBCode)
    }
}
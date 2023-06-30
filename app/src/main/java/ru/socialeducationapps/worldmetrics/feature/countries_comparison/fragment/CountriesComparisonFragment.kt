package ru.socialeducationapps.worldmetrics.feature.countries_comparison.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.countries_comparison.viewmodel.CountriesComparisonViewModel
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment

class CountriesComparisonFragment : InjectableFragment(R.layout.comparison_screen) {
    private val model by viewModels<CountriesComparisonViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
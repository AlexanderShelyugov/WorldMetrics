package ru.socialeducationapps.worldmetrics.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.CountriesListWithIndexAdapter
import ru.socialeducationapps.worldmetrics.adapter.CountriesListWithIndexDataItem
import ru.socialeducationapps.worldmetrics.global.ColorAccess.Companion.VALUE_DEFAULT_COLOR_RANGE
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.CODES_TO_NAMES
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.model.SimpleCountryValue

abstract class CountriesListWithIndexFragment :
    InjectableFragment(R.layout.countries_list_with_index) {
    private val countriesAdapter = CountriesListWithIndexAdapter(this::onCountryClick).apply {
        sortByCountry = true
        naturalOrder = true
        setColorsRange(VALUE_DEFAULT_COLOR_RANGE)
    }
    private lateinit var sortTypeItem: MenuItem
    private lateinit var sortOrderItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesAdapter.setValuesRange(getValueRange())
        view.findViewById<RecyclerView>(R.id.rv_countries_list).apply {
            adapter = countriesAdapter
        }
        lifecycleScope.launch {
            getData().collectLatest { countries ->
                val ctx = requireContext()
                countries.asSequence()
                    .map {
                        val name = CODES_TO_NAMES[it.iso3CountyCode]?.run(ctx::getString) ?: ""
                        val value = it.value
                        CountriesListWithIndexDataItem(it.iso3CountyCode, name, value)
                    }
                    .toList()
                    .run(countriesAdapter::setData)
            }
        }
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_country_list, menu)
        (menu.findItem(R.id.action_search).actionView as SearchView)
            .setOnQueryTextListener(onSearchListener)
        sortTypeItem = menu.findItem(R.id.action_sort_type)
        sortOrderItem = menu.findItem(R.id.action_sort_order)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sort_type -> {
            switchSortType()
            true
        }
        R.id.action_sort_order -> {
            switchSortOrder()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun switchSortType() {
        val weWillSortByCountry = !countriesAdapter.sortByCountry
        val nextIcon = if (weWillSortByCountry) {
            R.drawable.sort_alphabetical_variant
        } else {
            R.drawable.sort_numeric_variant
        }
        countriesAdapter.sortByCountry = weWillSortByCountry
        countriesAdapter.refresh()
        sortTypeItem.setIcon(nextIcon)
    }

    private fun switchSortOrder() {
        val weWillSortByNaturalOrder = !countriesAdapter.naturalOrder
        val nextIcon = if (weWillSortByNaturalOrder) {
            R.drawable.sort_variant
        } else {
            R.drawable.sort_reverse_variant
        }
        countriesAdapter.naturalOrder = weWillSortByNaturalOrder
        countriesAdapter.refresh()
        sortOrderItem.setIcon(nextIcon)
    }

    protected abstract fun getData(): Flow<List<SimpleCountryValue>>

    protected abstract fun getValueRange(): FeatureRange

    protected open fun onCountryClick(v: View, countryCode: String) {
        val extras = FragmentNavigatorExtras(
            v to requireContext().getString(R.string.transition_name_target_screen)
        )
        getNavigationOnClick(countryCode)?.let { action ->
            findNavController().navigate(action, extras)
        }
    }

    protected open fun getNavigationOnClick(countryCode: String): NavDirections? = null

    private val onSearchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            countriesAdapter.searchWith(query ?: "")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean = onQueryTextSubmit(newText)
    }
}
package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.CountriesListWithIndexDataItem
import ru.alexander.worldmetrics.adapter.CountriesListWithIndexAdapter
import ru.alexander.worldmetrics.global.ColorAccess.Companion.VALUE_DEFAULT_COLOR_RANGE
import ru.alexander.worldmetrics.model.CountriesData.Companion.CODES_TO_NAMES
import ru.alexander.worldmetrics.model.indexes.FeatureRange

abstract class CountriesListWithIndexFragment : Fragment(R.layout.countries_list_with_index) {
    private val countriesAdapter = CountriesListWithIndexAdapter(this::onCountryClick)
        .also {
            it.sortByCountry = true
            it.naturalOrder = true
            it.setColorsRange(VALUE_DEFAULT_COLOR_RANGE)
        }
    private lateinit var sortTypeItem: MenuItem
    private lateinit var sortOrderItem: MenuItem
    private lateinit var listView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById<RecyclerView>(R.id.rv_countries_list).also {
            it.adapter = countriesAdapter
        }
        countriesAdapter.setValuesRange(getValueRange())
        getData().observe(viewLifecycleOwner) { countries ->
            val ctx = requireContext()
            val data = countries.asSequence()
                .map {
                    val name = CODES_TO_NAMES[it.key]?.run(ctx::getString) ?: ""
                    val value = it.value.toFloatOrNull() ?: Float.NaN
                    CountriesListWithIndexDataItem(it.key, name, value)
                }
                .toList()
            countriesAdapter.setData(data)
        }
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
        else -> {
            super.onOptionsItemSelected(item)
        }
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

    protected abstract fun getData(): LiveData<Map<String, String>>

    protected abstract fun getValueRange(): FeatureRange

    protected abstract fun onCountryClick(country: String)

    private val onSearchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            countriesAdapter.searchWith(query ?: "")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean = onQueryTextSubmit(newText)
    }
}
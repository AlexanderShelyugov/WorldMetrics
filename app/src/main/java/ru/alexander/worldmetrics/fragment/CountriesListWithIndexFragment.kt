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
import ru.alexander.worldmetrics.adapter.CountriesListWithIndexViewAdapter
import ru.alexander.worldmetrics.global.ColorAccess.Companion.VALUE_DEFAULT_COLOR_RANGE
import ru.alexander.worldmetrics.model.indexes.FeatureRange

abstract class CountriesListWithIndexFragment :
    Fragment(R.layout.countries_list_with_index), SearchView.OnQueryTextListener {
    private val countriesAdapter = CountriesListWithIndexViewAdapter(this::onCountryClick)
    private lateinit var sortTypeItem: MenuItem
    private lateinit var sortOrderItem: MenuItem
    private lateinit var listView: RecyclerView

    init {
        countriesAdapter.run {
            sortByCountry = true
            naturalOrder = true
            setColorsRange(VALUE_DEFAULT_COLOR_RANGE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById<RecyclerView>(R.id.rv_countries_list).also {
            it.adapter = countriesAdapter
            it.itemAnimator = null
        }
        countriesAdapter.setValuesRange(getValueRange())
        countriesAdapter.reSort()
        getData().observe(viewLifecycleOwner) { countries ->
            countriesAdapter.setData(countries)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_country_list, menu)
        (menu.findItem(R.id.action_search).actionView as SearchView).setOnQueryTextListener(this)

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
        countriesAdapter.reSort()
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
        countriesAdapter.reSort()
        sortOrderItem.setIcon(nextIcon)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        countriesAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        countriesAdapter.filter.filter(newText)
        return false
    }

    protected abstract fun getData(): LiveData<Map<String, String>>

    protected abstract fun getValueRange(): FeatureRange

    protected abstract fun onCountryClick(country: String)
}
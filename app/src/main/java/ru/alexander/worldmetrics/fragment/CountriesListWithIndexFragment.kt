package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.CountriesListWithIndexViewAdapter

abstract class CountriesListWithIndexFragment :
    InjectableFragment(R.layout.countries_list_with_index), SearchView.OnQueryTextListener {
    private val countriesAdapter = CountriesListWithIndexViewAdapter(this::onCountryClick)
    private lateinit var sortTypeButton: ImageButton
    private lateinit var sortOrderButton: ImageButton
    private lateinit var listView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById<RecyclerView>(R.id.rv_countries_list).also {
            it.adapter = countriesAdapter
            it.itemAnimator = null
        }
        countriesAdapter.sortByCountry = true
        countriesAdapter.naturalOrder = true
        countriesAdapter.reSort()
        view.findViewById<SearchView>(R.id.sv_search)
            .setOnQueryTextListener(this)
        sortTypeButton = view.findViewById<ImageButton>(R.id.ib_sort_type).also {
            it.setOnClickListener { switchSortType() }
        }
        sortOrderButton = view.findViewById<ImageButton>(R.id.ib_sort_order).also {
            it.setOnClickListener { switchSortOrder() }
        }
        getData().observe(viewLifecycleOwner) { countries ->
            countriesAdapter.setData(countries)
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
        sortTypeButton.setImageResource(nextIcon)
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
        sortOrderButton.setImageResource(nextIcon)
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

    protected abstract fun onCountryClick(country: String)
}
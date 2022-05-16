package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.CountriesListWithIndexViewAdapter
import ru.alexander.worldmetrics.model.KeyValueList

abstract class CountriesListWithIndexFragment :
    InjectableFragment(R.layout.countries_list_with_index) {
    private val countriesAdapter = CountriesListWithIndexViewAdapter(this::onCountryClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rv_countries_list).run {
            adapter = countriesAdapter
        }
        getData().observe(viewLifecycleOwner) { countries ->
            countriesAdapter.setData(KeyValueList(countries))
        }
    }

    protected abstract fun getData(): LiveData<Map<String, String>>

    protected abstract fun onCountryClick(country: String)
}
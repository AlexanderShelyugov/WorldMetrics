package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.DemocracyIndexOverviewViewAdapter
import ru.alexander.worldmetrics.model.KeyValueList
import ru.alexander.worldmetrics.viewmodels.DemocracyIndexOverviewViewModel

class DemocracyIndexOverviewFragment : InjectableFragment(R.layout.countries_list_democracy_index) {
    private lateinit var layoutManager: LayoutManager
    private val adapter = DemocracyIndexOverviewViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countriesListView = view.findViewById<RecyclerView>(R.id.rv_countries_list)
        layoutManager = LinearLayoutManager(context)
        countriesListView.layoutManager = layoutManager
        countriesListView.adapter = adapter

        val model: DemocracyIndexOverviewViewModel by activityViewModels()
        val observer = Observer<Map<String, String>> { countries ->
            // update UI
            Log.i("Countries list", "Value updated")
            adapter.setData(KeyValueList(countries))
        }
        model.lastYearData.observe(viewLifecycleOwner, observer)
    }
}
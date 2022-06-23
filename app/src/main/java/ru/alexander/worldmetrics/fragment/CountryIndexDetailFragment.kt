package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.adapter.IndexFeaturesRVAdapter

abstract class CountryIndexDetailFragment<T> : Fragment(R.layout.country_detail_indexes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val featuresListView = requireView().findViewById<RecyclerView>(R.id.rv_content)
        val adapter = getAdapter()
        getData().observe(viewLifecycleOwner) {
            adapter.setData(it)
            adapter.setValueColors(getFeatureColors())
        }
        featuresListView.adapter = adapter
    }

    protected abstract fun getCountryCode(): String

    protected abstract fun getData(): LiveData<List<T>>

    protected abstract fun getAdapter(): IndexFeaturesRVAdapter<T>

    protected open fun getFeatureColors(): List<Int> {
        return emptyList()
    }
}
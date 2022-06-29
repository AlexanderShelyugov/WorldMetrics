package ru.socialeducationapps.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange

abstract class CountryIndexDetailFragment<T> : Fragment(R.layout.country_detail_indexes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val featuresListView = requireView().findViewById<RecyclerView>(R.id.rv_content)
        val adapter = getAdapter()
        adapter.setFeatureRanges(getFeatureRanges())
        getData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        featuresListView.adapter = adapter
    }

    protected abstract fun getCountryCode(): String

    protected abstract fun getData(): LiveData<List<T>>

    protected abstract fun getAdapter(): IndexFeaturesRVAdapter<T>

    protected open fun getFeatureRanges(): List<FeatureRange> {
        return emptyList()
    }
}
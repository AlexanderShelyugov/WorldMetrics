package ru.socialeducationapps.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange

abstract class CountryIndexDetailFragment<T> : Fragment(R.layout.country_detail_indexes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = getAdapter()
        adapter.setFeatureRanges(getFeatureRanges())
        lifecycleScope.launch {
            getData().collectLatest { items ->
                adapter.setData(items)
            }
        }
        requireView().findViewById<RecyclerView>(R.id.rv_content).adapter = adapter
    }

    protected abstract fun getCountryCode(): String

    protected abstract fun getData(): Flow<List<T>>

    protected abstract fun getAdapter(): IndexFeaturesRVAdapter<T>

    protected open fun getFeatureRanges(): List<FeatureRange> {
        return emptyList()
    }
}
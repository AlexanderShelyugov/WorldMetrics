package ru.socialeducationapps.worldmetrics.modules.indexes.fragment

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.model.CountriesData.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.modules.indexes.model.FeatureRange
import ru.socialeducationapps.worldmetrics.modules.indexes.viewmodel.CommonCountryDetailViewModel

abstract class CountryIndexDetailFragment<T> : InjectableFragment(R.layout.country_detail_indexes) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ctx = requireContext()
        sharedElementEnterTransition = MaterialContainerTransform(ctx, true)
            .apply {
                duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
                scrimColor = TRANSPARENT
                setAllContainerColors(TRANSPARENT)
                setPathMotion(MaterialArcMotion())
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = getAdapter()
        adapter.setFeatureRanges(getFeatureRanges())
        lifecycleScope.launch {
            getData().collectLatest { items ->
                adapter.setData(items)
            }
        }
        requireView().run {
            findViewById<TextView>(R.id.tv_country_name).apply {
                transitionName =
                    context.getString(R.string.transition_name_country_name_detail)
                getNameIdByCode(getCountryCode())?.run(this::setText)
            }
            findViewById<RecyclerView>(R.id.rv_content).adapter = adapter
        }
        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private val model: CommonCountryDetailViewModel<T>
        get() = getCountryDetailViewModel()

    protected abstract fun getCountryCode(): String
    protected abstract fun getCountryDetailViewModel(): CommonCountryDetailViewModel<T>
    private fun getData() = model
        .apply { setCountry(getCountryCode()) }
        .run { allData }

    private fun getFeatureRanges(): List<FeatureRange> =
        model.getFeatureRanges()

    private fun getAdapter(): IndexFeaturesRVAdapter<T> =
        IndexFeaturesRVAdapter(model.indexLayout)
}
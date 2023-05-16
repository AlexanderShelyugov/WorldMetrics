package ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.IndexFeaturesRVAdapter
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.FeatureRange
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryDetailViewModel

abstract class CountryIndexDetailFragment<T> : InjectableFragment(R.layout.country_detail_indexes) {
    private lateinit var spinner: ViewGroup
    private lateinit var contentView: ViewGroup

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
        val adapter = getAdapter().apply {
            setFeatureRanges(getFeatureRanges())
        }
        requireView().run {
            spinner = findViewById(R.id.fl_loading)
            contentView = findViewById(R.id.ll_content)
            findViewById<TextView>(R.id.tv_country_name).apply {
                transitionName =
                    context.getString(R.string.transition_name_country_name_detail)
                getNameIdByCode(getCountryCode())?.run(this::setText)
            }
            findViewById<RecyclerView>(R.id.rv_indexes).adapter = adapter
        }
        lifecycleScope.launch {
            getData().collectLatest { items ->
                setState(items != null)
                adapter.setData(items ?: emptyList())
            }
        }
        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun setState(contentReady: Boolean) {
        val transitionOut = Fade()
        val transitionIn = Fade()
            .apply { startDelay = transitionOut.duration / 2 }
        val outgoingView =
            if (contentReady) spinner else contentView
        val incomingView =
            if (contentReady) contentView else spinner

        TransitionManager.beginDelayedTransition(outgoingView, transitionOut)
        TransitionManager.beginDelayedTransition(incomingView, transitionIn)
        outgoingView.isVisible = false
        incomingView.isVisible = true
        setHasOptionsMenu(contentReady)
        requireActivity().invalidateOptionsMenu()
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
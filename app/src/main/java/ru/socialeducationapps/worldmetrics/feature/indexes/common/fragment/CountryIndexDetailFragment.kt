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
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter.Companion.AdapterState
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.LabelValueChartRVAdapter.Companion.IndexFeatureAdapterItem
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonCountryIndexDetailViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CountryIndexDetailViewModel.Companion.ViewState.Success

abstract class CountryIndexDetailFragment<IndexType> :
    InjectableFragment(R.layout.country_detail_indexes) {

    protected abstract fun getCountryCode(): String
    protected abstract fun getCountryDetailViewModel(): CommonCountryIndexDetailViewModel<IndexType>

    private lateinit var spinner: ViewGroup
    private lateinit var contentView: ViewGroup
    private val model: CommonCountryIndexDetailViewModel<IndexType>
        get() = getCountryDetailViewModel()

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
        model.setCountry(getCountryCode())
        model.onOpen()
        val adapter = LabelValueChartRVAdapter<IndexType>()
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
            model.getViewState().collect { viewState ->
                if (viewState !is Success) {
                    adapter.setState(AdapterState(emptyList()))
                    setContentReady(false)
                    return@collect
                }
                val successState: Success<IndexType> = viewState
                val adapterState = model.indexLayout.features
                    .map { feature ->
                        IndexFeatureAdapterItem(
                            feature.first,
                            successState.allData,
                            feature.second,
                            model.getColorCalculatorsForFeatures()[feature.first]
                        )
                    }.toList()
                    .let { AdapterState(it) }
                adapter.setState(adapterState)
                setContentReady(true)
            }
        }
        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun setContentReady(contentReady: Boolean) {
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
}
package ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager.beginDelayedTransition
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.country.rv_adapter.CountriesListWithIndexAdapter
import ru.socialeducationapps.worldmetrics.feature.country.rv_adapter.CountriesListWithIndexDataItem
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.feature.helper.rv_adapter.ScrollToTopOnChangeObserver
import ru.socialeducationapps.worldmetrics.feature.helper.utils.ColorAccess
import ru.socialeducationapps.worldmetrics.feature.helper.utils.ToastHelper
import ru.socialeducationapps.worldmetrics.feature.helper.utils.hideKeyboard
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.LOADING
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.LOADING_ERROR
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.OverviewViewModel.Companion.DataStatus.NOT_INITIALIZED
import javax.inject.Inject

abstract class CountriesListWithIndexFragment :
    InjectableFragment(R.layout.countries_list_with_index) {
    @Inject
    lateinit var dispatcherProvider: DispatcherProvider
    private val countriesAdapter = CountriesListWithIndexAdapter(this::onCountryClick).apply {
        sortByCountry = true
        naturalOrder = true
    }
    private lateinit var sortTypeItem: MenuItem
    private lateinit var sortOrderItem: MenuItem
    private lateinit var spinner: ViewGroup
    private lateinit var contentView: ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = view.findViewById(R.id.fl_loading)
        view.findViewById<RecyclerView>(R.id.rv_countries_list).apply {
            contentView = this
            adapter = countriesAdapter.also {
                it.registerAdapterDataObserver(ScrollToTopOnChangeObserver(this))
                it.processInBackground(lifecycleScope, dispatcherProvider)
                it.setColorCalculator(
                    getOverviewViewModel().getColorCalculator().also { colorCalculator ->
                        val colorRange = ColorAccess.getDefaultColorRange(requireContext())
                        colorCalculator.setColorRange(colorRange.first, colorRange.second)
                    }
                )
            }
        }
        lifecycleScope.launch {
            getOverviewViewModel().getLastYearData().collect {
                val ctx = requireContext()
                when (it.status) {
                    NOT_INITIALIZED, LOADING -> {
                        setIsContentReady(false)
                        return@collect
                    }

                    LOADING_ERROR -> {
                        setIsContentReady(false)
                        ToastHelper.show(ctx, it.error)
                        return@collect
                    }

                    else -> {}
                }

                val data = it.data.asSequence()
                    .map { dataForCountry ->
                        val iso3Code = dataForCountry.iso3CountryCode.lowercase()
                        val name = getNameIdByCode(iso3Code)?.run(ctx::getString) ?: ""
                        val value = dataForCountry.value
                        CountriesListWithIndexDataItem(iso3Code, name, value)
                    }
                    .toList()
                countriesAdapter.setData(data)
                setIsContentReady(true)
            }
        }
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_country_list, menu)
        (menu.findItem(R.id.action_search).actionView as SearchView)
            .setOnQueryTextListener(onSearchListener)
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

        else -> super.onOptionsItemSelected(item)
    }

    private fun setIsContentReady(contentReady: Boolean) {
        val transitionOut = Fade()
        val transitionIn = Fade()
            .apply { startDelay = transitionOut.duration / 2 }
        val outgoingView =
            if (contentReady) spinner else contentView
        val incomingView =
            if (contentReady) contentView else spinner

        beginDelayedTransition(outgoingView, transitionOut)
        beginDelayedTransition(incomingView, transitionIn)
        outgoingView.isVisible = false
        incomingView.isVisible = true
        setHasOptionsMenu(contentReady)
        requireActivity().invalidateOptionsMenu()
    }

    private fun switchSortType() {
        val weWillSortByCountry = !countriesAdapter.sortByCountry
        val nextIcon = if (weWillSortByCountry) {
            R.drawable.sort_alphabetical_variant
        } else {
            R.drawable.sort_numeric_variant
        }
        countriesAdapter.sortByCountry = weWillSortByCountry
        countriesAdapter.refresh()
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
        countriesAdapter.refresh()
        sortOrderItem.setIcon(nextIcon)
    }

    protected abstract fun getOverviewViewModel(): CommonOverviewViewModel

    protected open fun onCountryClick(v: View, countryCode: String) {
        val extras = FragmentNavigatorExtras(
            v to requireContext().getString(R.string.transition_name_target_screen)
        )
        requireActivity().hideKeyboard()
        getNavigationOnClick(countryCode)?.let { action ->
            findNavController().navigate(action, extras)
        }
    }

    protected open fun getNavigationOnClick(countryCode: String): NavDirections? = null

    private val onSearchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            countriesAdapter.searchWith(query ?: "")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean = onQueryTextSubmit(newText)
    }
}
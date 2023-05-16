package ru.socialeducationapps.worldmetrics.feature.indexes.common.fragment

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.CountriesListWithIndexAdapter
import ru.socialeducationapps.worldmetrics.adapter.CountriesListWithIndexDataItem
import ru.socialeducationapps.worldmetrics.adapter.ScrollToTopOnChangeObserver
import ru.socialeducationapps.worldmetrics.feature.coroutines.api.DispatcherProvider
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.feature.helper.utils.ColorAccess.Companion.VALUE_DEFAULT_COLOR_RANGE
import ru.socialeducationapps.worldmetrics.feature.helper.utils.hideKeyboard
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.CountryResourceBindings.Companion.getNameIdByCode
import ru.socialeducationapps.worldmetrics.feature.indexes.common.model.SimpleCountryValue
import ru.socialeducationapps.worldmetrics.feature.indexes.common.viewmodel.CommonOverviewViewModel
import javax.inject.Inject

abstract class CountriesListWithIndexFragment :
    InjectableFragment(R.layout.countries_list_with_index) {
    @Inject
    lateinit var dispatcherProvider: DispatcherProvider
    private val countriesAdapter = CountriesListWithIndexAdapter(this::onCountryClick).apply {
        sortByCountry = true
        naturalOrder = true
        setColorsRange(VALUE_DEFAULT_COLOR_RANGE)
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
                it.setValuesRange(getValueRange())
                it.registerAdapterDataObserver(ScrollToTopOnChangeObserver(this))
                it.processInBackground(lifecycleScope, dispatcherProvider)
            }
        }
        lifecycleScope.launch {
            getData().collectLatest { countries ->
                val ctx = requireContext()
                val data = countries?.asSequence()
                    ?.map {
                        val code = it.iso3CountryCode.lowercase()
                        val name = getNameIdByCode(code)?.run(ctx::getString) ?: ""
                        val value = it.value
                        CountriesListWithIndexDataItem(code, name, value)
                    }
                    ?.toList()
                countriesAdapter.setData(data ?: emptyList())
                setState(countries != null)
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
    private val model: CommonOverviewViewModel
        get() = getOverviewViewModel()

    private fun getData(): Flow<List<SimpleCountryValue>?> = model.lastYearData
    protected fun getValueRange() = model.getValueRange()

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
package ru.socialeducationapps.worldmetrics.feature.home.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.socialeducationapps.worldmetrics.NavGraphDirections.Companion.actionGlobalToBeImplementedFragment
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.indexes.all.rv_adapter.IndexListAdapter
import ru.socialeducationapps.worldmetrics.feature.indexes.all.rv_adapter.IndexListAdapter.IndexListItem
import ru.socialeducationapps.worldmetrics.feature.country.current.viewmodel.CurrentCountryViewModel
import ru.socialeducationapps.worldmetrics.feature.helper.fragment.InjectableFragment
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToCorruptionPerceptionsCountryDetail
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToGDPCountryDetail
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToPopulationDetail
import ru.socialeducationapps.worldmetrics.feature.home.fragment.HomeScreenFragmentDirections.Companion.actionHomeScreenToPressFreedomCountryDetail
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.AllIndexes.Companion.ALL_INDEXES
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.DataBindingForIndexGroup

@AndroidEntryPoint
class IndexesForCountryFragment : InjectableFragment(R.layout.indexes_for_country) {
    private val model by viewModels<CurrentCountryViewModel>()
    private lateinit var contentContainer: ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentContainer = requireView().findViewById(R.id.ll_indexes)
        model.currentCountryCode.observe(viewLifecycleOwner) { countryCode ->
            val someCountrySelected = countryCode?.isNotBlank() ?: false
            contentContainer.apply {
                removeAllViews()
                isVisible = someCountrySelected
            }
            if (someCountrySelected) {
                val actions = getIndexesActions(countryCode)
                ALL_INDEXES.forEach { addIndexGroup(it, actions) }
            }
        }
    }

    private fun addIndexGroup(
        indexGroup: DataBindingForIndexGroup,
        actions: Map<Int, NavDirections>
    ) {
        val indexGroupView: View = layoutInflater.inflate(
            R.layout.index_group_for_country,
            contentContainer,
            false
        )

        indexGroupView
            .findViewById<TextView>(R.id.tv_index_group_name)
            .apply {
                setText(indexGroup.name)
                setTextColor(requireContext().getColor(indexGroup.itemColor))
            }
        val navController = findNavController()
        val indexesList = indexGroupView.findViewById<RecyclerView>(R.id.rv_indexes)
        IndexListAdapter().apply {
            itemColor = indexGroup.itemColor
            textColor = indexGroup.color
            data = indexGroup.indexes.asSequence()
                .map { groupItem ->
                    IndexListItem(getString(groupItem.name)) {
                        navController.navigate(
                            actions[groupItem.name] ?: actionGlobalToBeImplementedFragment()
                        )
                    }
                }
                .toList()
            indexesList.adapter = this
        }

        contentContainer.addView(indexGroupView)
    }

    private fun getIndexesActions(countryCode: String) = mapOf(
        R.string.index_name_population to actionHomeScreenToPopulationDetail(countryCode),
        R.string.index_name_corruption_perceptions to actionHomeScreenToCorruptionPerceptionsCountryDetail(
            countryCode
        ),
        R.string.index_name_democracy to actionHomeScreenToDemocracyIndexCountryDetail(countryCode),
        R.string.index_name_press_freedom to actionHomeScreenToPressFreedomCountryDetail(countryCode),
        R.string.index_name_gdp to actionHomeScreenToGDPCountryDetail(countryCode),
    )
}
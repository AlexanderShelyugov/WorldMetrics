package ru.socialeducationapps.worldmetrics.fragment.home_screen

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.adapter.IndexListAdapter
import ru.socialeducationapps.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionGlobalToBeImplementedFragment
import ru.socialeducationapps.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToCorruptionPerceptionsCountryDetail
import ru.socialeducationapps.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToDemocracyIndexCountryDetail
import ru.socialeducationapps.worldmetrics.fragment.home_screen.HomeScreenFragmentDirections.Companion.actionHomeScreenToPressFreedomCountryDetail
import ru.socialeducationapps.worldmetrics.global.NavigationHelper.Companion.navigateTo
import ru.socialeducationapps.worldmetrics.model.indexes.AllIndexes.Companion.ALL_INDEXES
import ru.socialeducationapps.worldmetrics.model.indexes.IndexGroupForCountryData
import ru.socialeducationapps.worldmetrics.viewmodel.CurrentCountryViewModel

class IndexesForCountryFragment : Fragment(R.layout.indexes_for_country) {
    private lateinit var contentContainer: ViewGroup
    lateinit var transitionTV: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: CurrentCountryViewModel by activityViewModels()
        contentContainer = requireView().findViewById(R.id.ll_indexes)
        model.currentCountryCode.observe(viewLifecycleOwner) { countryCode ->
            contentContainer.removeAllViews()
            val someCountrySelected = countryCode?.isNotBlank() ?: false
            contentContainer.isVisible = someCountrySelected
            if (someCountrySelected) {
                val actions = getIndexesActions(countryCode)
                ALL_INDEXES.forEach { addIndexGroup(it, actions) }
            }
        }
    }

    private fun addIndexGroup(
        indexGroup: IndexGroupForCountryData,
        actions: Map<Int, NavDirections>
    ) {
        val indexGroupView: View = layoutInflater.inflate(
            R.layout.index_group_for_country,
            contentContainer,
            false
        )

        val groupHeader = indexGroupView.findViewById<TextView>(R.id.tv_index_group_name)
        groupHeader.setText(indexGroup.name)
        groupHeader.setTextColor(requireContext().getColor(indexGroup.itemColor))

        val indexesList = indexGroupView.findViewById<RecyclerView>(R.id.rv_indexes)
        val adapter = IndexListAdapter()
        adapter.itemColor = indexGroup.itemColor
        adapter.textColor = indexGroup.color
        adapter.data = indexGroup.indexes.asSequence()
            .map { groupItem ->
                IndexListAdapter.IndexListItem(
                    name = getString(groupItem.name)
                ) { navigateTo(it.findNavController(), actions[groupItem.name]!!) }
            }
            .toList()
        indexesList.adapter = adapter

        contentContainer.addView(indexGroupView)
    }

    private fun getIndexesActions(countryCode: String) = mapOf(
        R.string.index_name_demographics to actionGlobalToBeImplementedFragment(),
        R.string.index_name_corruption_perceptions to actionHomeScreenToCorruptionPerceptionsCountryDetail(
            countryCode
        ),
        R.string.index_name_democracy to actionHomeScreenToDemocracyIndexCountryDetail(countryCode),
        R.string.index_name_press_freedom to actionHomeScreenToPressFreedomCountryDetail(countryCode),
        R.string.index_name_gdp to actionGlobalToBeImplementedFragment(),
        R.string.index_name_some_business to actionGlobalToBeImplementedFragment()
    )
}
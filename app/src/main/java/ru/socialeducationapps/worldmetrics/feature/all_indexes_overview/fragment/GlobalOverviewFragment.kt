package ru.socialeducationapps.worldmetrics.feature.all_indexes_overview.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.feature.all_indexes_overview.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewToCorruptionPerceptionsOverview
import ru.socialeducationapps.worldmetrics.feature.all_indexes_overview.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewToDemocracyIndexOverview
import ru.socialeducationapps.worldmetrics.feature.all_indexes_overview.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewToGDPCountryList
import ru.socialeducationapps.worldmetrics.feature.all_indexes_overview.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewToPopulationCountryList
import ru.socialeducationapps.worldmetrics.feature.all_indexes_overview.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewToPressFreedomOverview
import ru.socialeducationapps.worldmetrics.feature.all_indexes_overview.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalToBeImplementedFragment
import ru.socialeducationapps.worldmetrics.feature.helper.utils.ColorAccess.Companion.setColorFilter
import ru.socialeducationapps.worldmetrics.feature.indexes.all.model.AllIndexes.Companion.ALL_INDEXES

class GlobalOverviewFragment : Fragment(R.layout.all_indexes) {
    private lateinit var allIndexesContainer: ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allIndexesContainer = requireView().findViewById(R.id.ll_indexes)
        inflateIndexes()
    }

    private fun inflateIndexes() {
        val ctx = requireContext()
        ALL_INDEXES.forEach { indexGroup ->
            indexGroup.indexes.forEach { index ->
                val button = layoutInflater.inflate(
                    R.layout.all_indexes_item,
                    allIndexesContainer,
                    false
                )
                button.findViewById<TextView>(R.id.tv_index_name).run {
                    setText(index.name)
                    setTextColor(ctx.getColor(indexGroup.color))
                }
                val color = ctx.getColor(indexGroup.itemColor)
                setColorFilter(button.background, color)
                val action: NavDirections = INDEX_ACTIONS[index.name]
                    ?: actionGlobalToBeImplementedFragment()
                button.setOnClickListener { findNavController().navigate(action) }
                allIndexesContainer.addView(button)
            }
        }
    }

    private companion object {
        val INDEX_ACTIONS = mapOf(
            R.string.index_name_population to actionGlobalOverviewToPopulationCountryList(),
            R.string.index_name_corruption_perceptions to actionGlobalOverviewToCorruptionPerceptionsOverview(),
            R.string.index_name_democracy to actionGlobalOverviewToDemocracyIndexOverview(),
            R.string.index_name_press_freedom to actionGlobalOverviewToPressFreedomOverview(),
            R.string.index_name_gdp to actionGlobalOverviewToGDPCountryList(),
        )
    }
}
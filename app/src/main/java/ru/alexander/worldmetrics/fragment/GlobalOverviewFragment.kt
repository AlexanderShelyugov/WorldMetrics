package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewFragmentToCorruptionPerceptionsOverviewFragment
import ru.alexander.worldmetrics.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewFragmentToDemocracyIndexOverviewFragment
import ru.alexander.worldmetrics.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalOverviewFragmentToPressFreedomOverviewFragment
import ru.alexander.worldmetrics.fragment.GlobalOverviewFragmentDirections.Companion.actionGlobalToBeImplementedFragment
import ru.alexander.worldmetrics.global.ColorAccess.Companion.setColorFilter
import ru.alexander.worldmetrics.model.indexes.AllIndexes.Companion.ALL_INDEXES

class GlobalOverviewFragment : Fragment(R.layout.all_indexes) {
    private lateinit var allIndexesContainer: ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allIndexesContainer = requireView().findViewById(R.id.ll_indexes)
        inflateIndexes()
    }

    private fun inflateIndexes() {
        ALL_INDEXES.forEach { indexGroup ->
            indexGroup.indexes.forEach { index ->
                val button = layoutInflater.inflate(
                    R.layout.index_for_country_list_item,
                    allIndexesContainer,
                    false
                )
                button.findViewById<TextView>(R.id.tv_index_name).setText(index.name)
                val color = requireContext().getColor(indexGroup.itemColor)
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
            R.string.index_name_corruption_perceptions to actionGlobalOverviewFragmentToCorruptionPerceptionsOverviewFragment(),
            R.string.index_name_democracy to actionGlobalOverviewFragmentToDemocracyIndexOverviewFragment(),
            R.string.index_name_press_freedom to actionGlobalOverviewFragmentToPressFreedomOverviewFragment()
        )
    }
}
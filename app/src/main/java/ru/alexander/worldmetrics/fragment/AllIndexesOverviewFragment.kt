package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.AllIndexesOverviewFragmentDirections.Companion.actionAllIndexesOverviewFragmentToCorruptionPerceptionsOverviewFragment
import ru.alexander.worldmetrics.fragment.AllIndexesOverviewFragmentDirections.Companion.actionAllIndexesOverviewFragmentToDemocracyIndexOverviewFragment
import ru.alexander.worldmetrics.fragment.AllIndexesOverviewFragmentDirections.Companion.actionAllIndexesOverviewFragmentToPressFreedomOverviewFragment
import ru.alexander.worldmetrics.global.NavigationHelper.Companion.bindNavigation

class AllIndexesOverviewFragment : Fragment(R.layout.all_indexes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireView().findViewById<ViewGroup>(R.id.ll_indexes).let { container ->
            INDEXES.forEach { index ->
                val button = layoutInflater.inflate(R.layout.button_default, container, false) as Button
                button.text = getString(index.first)
                bindNavigation(button, index.second)
                container.addView(button)
            }
        }
    }

    private companion object {
        val INDEXES = listOf(
            R.string.democracy_index_name to actionAllIndexesOverviewFragmentToDemocracyIndexOverviewFragment(),
            R.string.corruption_perceptions_index_name to actionAllIndexesOverviewFragmentToCorruptionPerceptionsOverviewFragment(),
            R.string.press_freedom_index_name to actionAllIndexesOverviewFragmentToPressFreedomOverviewFragment()
        )
    }
}
package ru.alexander.worldmetrics.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.alexander.worldmetrics.R
import ru.alexander.worldmetrics.fragment.AllIndexesOverviewFragmentDirections.Companion.actionAllIndexesOverviewFragmentToCorruptionPerceptionsOverviewFragment
import ru.alexander.worldmetrics.fragment.AllIndexesOverviewFragmentDirections.Companion.actionAllIndexesOverviewFragmentToDemocracyIndexOverviewFragment
import ru.alexander.worldmetrics.fragment.AllIndexesOverviewFragmentDirections.Companion.actionAllIndexesOverviewFragmentToPressFreedomOverviewFragment
import ru.alexander.worldmetrics.navigation.NavigationHelper.Companion.bindNavigation

class AllIndexesOverviewFragment : Fragment(R.layout.all_indexes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val view = requireView()
        bindNavigation(
            view,
            R.id.btn_goto_democracy_index,
            actionAllIndexesOverviewFragmentToDemocracyIndexOverviewFragment()
        )
        bindNavigation(
            view,
            R.id.btn_goto_corruption_perceptions,
            actionAllIndexesOverviewFragmentToCorruptionPerceptionsOverviewFragment()
        )
        bindNavigation(
            view,
            R.id.btn_goto_press_freedom,
            actionAllIndexesOverviewFragmentToPressFreedomOverviewFragment()
        )
    }
}